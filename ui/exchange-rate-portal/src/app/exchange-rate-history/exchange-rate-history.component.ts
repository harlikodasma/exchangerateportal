import { Component } from '@angular/core';
import { CurrencyService } from '../_services/currency.service';
import { Currency } from '../_models/currency';
import { map, Observable, startWith, tap } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { FormControl } from '@angular/forms';
import { ExchangeRateService } from '../_services/exchange-rate.service';
import { ExchangeRate } from '../_models/exchange-rate';

@Component({
  selector: 'app-exchange-rate-history',
  templateUrl: './exchange-rate-history.component.html',
  styleUrls: ['./exchange-rate-history.component.scss']
})
export class ExchangeRateHistoryComponent {

  formControl: FormControl = new FormControl('');
  currencyList: Currency[] = [];
  exchangeRateList: ExchangeRate[] = [];
  filteredCurrencyList: Observable<Currency[]> = new Observable<Currency[]>();
  showHistoryTable: boolean = false;
  displayedColumns: string[] = ['date', 'rateAgainstEur'];

  constructor(private currencyService: CurrencyService,
              private exchangeRateService: ExchangeRateService) {
    this.currencyService.getCurrencyList().pipe(
      tap((response: Currency[]) => {
        this.currencyList = this.alphabetizeCurrencyList(response);

        this.filteredCurrencyList = this.formControl.valueChanges.pipe(
          tap(value => this.showHistoryTable = value !== ''),
          startWith(''),
          map(value => this.filterCurrencyListByName(value || ''))
        );
      })
    ).subscribe();
  }

  onCurrencySelected(event: MatAutocompleteSelectedEvent): void {
    this.exchangeRateService.getExchangeRateHistoryForCurrency(event.option.id).pipe(
      tap((response: ExchangeRate[]) => {
        this.exchangeRateList = this.sortExchangeRatesByDate(response);
      })
    ).subscribe();
  }

  private alphabetizeCurrencyList(list: Currency[]): Currency[] {
    return list.sort((a, b) => {
      return (a.name > b.name) ? 1 : -1;
    });
  }

  private sortExchangeRatesByDate(list: ExchangeRate[]): ExchangeRate[] {
    return list.sort((a, b) => {
      return new Date(b.date).getTime() - new Date(a.date).getTime();
    });
  }

  private filterCurrencyListByName(value: string): Currency[] {
    const filterValue = value.toLowerCase();

    return this.currencyList.filter(currency => currency.name.toLowerCase().includes(filterValue));
  }

}
