import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { TranslateService } from '@ngx-translate/core';
import { CurrencyService } from '../_services/currency.service';
import { tap } from 'rxjs';
import { CurrencyCalculate } from '../_models/currency-calculate';

@Component({
  selector: 'app-currency-calculator',
  templateUrl: './currency-calculator.component.html',
  styleUrls: ['./currency-calculator.component.scss']
})
export class CurrencyCalculatorComponent {

  amountFormControl: FormControl = new FormControl('');
  isCurrencySelected: boolean = false;
  areValidationErrors: boolean = false;
  showResults: boolean = false;
  private selectedCurrency: string = '';
  private submittedCalculation?: { amount: number, currency: string };
  private fetchedCalculation?: CurrencyCalculate;

  constructor(private translate: TranslateService,
              private currencyService: CurrencyService) {
  }

  onCurrencySelected(event: MatAutocompleteSelectedEvent): void {
    this.selectedCurrency = event.option.id;
  }

  setCurrencySelected(event: boolean): void {
    this.isCurrencySelected = event;
  }

  onCalculate(): void {
    if (!this.isCurrencySelected || this.amountFormControl.value === '') {
      this.areValidationErrors = true;
    } else {
      this.areValidationErrors = false;
      this.showResults = true;
      this.submittedCalculation = {
        amount: this.amountFormControl.value,
        currency: this.selectedCurrency
      };

      this.currencyService.calculateCurrencyRate(this.submittedCalculation.amount, this.submittedCalculation.currency).pipe(
        tap((response: CurrencyCalculate) => {
          this.fetchedCalculation = response;
        })
      ).subscribe();
    }
  }

  generateResult(): string {
    return `${this.submittedCalculation?.amount}
            ${this.translate.instant('currency.calculator.eur')} = ${this.fetchedCalculation?.calculatedAmount}
            ${this.submittedCalculation?.currency}`;
  }

  generateResultExchangeRate(): string {
    return `${this.translate.instant('exchange.rate.common.rate.against.eur')}: ${this.fetchedCalculation?.rateAgainstEur}`;
  }

}
