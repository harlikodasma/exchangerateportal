import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { TranslateService } from '@ngx-translate/core';

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
  private submittedAmount?: number;
  private submittedCurrency: string = '';

  constructor(private translate: TranslateService) {
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
      this.submittedAmount = this.amountFormControl.value;
      this.submittedCurrency = this.selectedCurrency;
      this.showResults = true;
    }
  }

  generateResult(): string {
    return `${this.submittedAmount} ${this.translate.instant('currency.calculator.eur')} = amount ${this.submittedCurrency}`;
  }

  generateResultExchangeRate(): string {
    return `${this.translate.instant('exchange.rate.common.rate.against.eur')}: amount2`;
  }

}
