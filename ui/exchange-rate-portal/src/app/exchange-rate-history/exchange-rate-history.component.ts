import { Component } from '@angular/core';
import { tap } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { ExchangeRateService } from '../_services/exchange-rate.service';
import { ExchangeRate } from '../_models/exchange-rate';

@Component({
  selector: 'app-exchange-rate-history',
  templateUrl: './exchange-rate-history.component.html',
  styleUrls: ['./exchange-rate-history.component.scss']
})
export class ExchangeRateHistoryComponent {

  exchangeRateList: ExchangeRate[] = [];
  showHistoryTable: boolean = false;
  displayedColumns: string[] = ['date', 'rateAgainstEur'];

  constructor(private exchangeRateService: ExchangeRateService) {
  }

  setShowTable(event: boolean): void {
    this.showHistoryTable = event;
  }

  onCurrencySelected(event: MatAutocompleteSelectedEvent): void {
    this.exchangeRateService.getExchangeRateHistoryForCurrency(event.option.id).pipe(
      tap((response: ExchangeRate[]) => {
        this.exchangeRateList = this.sortExchangeRatesByDate(response);
      })
    ).subscribe();
  }

  private sortExchangeRatesByDate(list: ExchangeRate[]): ExchangeRate[] {
    return list.sort((a, b) => {
      return new Date(b.date).getTime() - new Date(a.date).getTime();
    });
  }

}
