import { Component } from '@angular/core';
import { CurrencyService } from '../_services/currency.service';
import { Currency } from '../_models/currency';
import { tap } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';

@Component({
  selector: 'app-exchange-rate-history',
  templateUrl: './exchange-rate-history.component.html',
  styleUrls: ['./exchange-rate-history.component.scss']
})
export class ExchangeRateHistoryComponent {

  currencyList: Currency[] = [];

  constructor(private currencyService: CurrencyService) {
    this.currencyService.getCurrencyList().pipe(
      tap((response: Currency[]) => {
        this.currencyList = this.alphabetizeCurrencyList(response);
      })
    ).subscribe();
  }

  onCurrencySelected(event: MatAutocompleteSelectedEvent): void {
    console.log(event.option.id);
  }

  private alphabetizeCurrencyList(list: Currency[]): Currency[] {
    return list.sort((a, b) => {
      return (a.name > b.name) ? 1 : -1;
    })
  }

}
