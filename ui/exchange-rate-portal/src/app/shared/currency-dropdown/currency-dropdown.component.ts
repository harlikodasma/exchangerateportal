import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { map, Observable, startWith, tap } from 'rxjs';
import { Currency } from '../../_models/currency';
import { CurrencyService } from '../../_services/currency.service';

@Component({
  selector: 'app-currency-dropdown',
  templateUrl: './currency-dropdown.component.html',
  styleUrls: ['./currency-dropdown.component.scss']
})
export class CurrencyDropdownComponent {

  @Output() currencySelectedEvent: EventEmitter<MatAutocompleteSelectedEvent> = new EventEmitter<MatAutocompleteSelectedEvent>;
  @Output() isCurrencySelected: EventEmitter<boolean> = new EventEmitter<boolean>;
  formControl: FormControl = new FormControl('');
  filteredCurrencyList: Observable<Currency[]> = new Observable<Currency[]>();
  private currencyList: Currency[] = [];

  constructor(private currencyService: CurrencyService) {
    this.currencyService.getCurrencyList().pipe(
      tap((response: Currency[]) => {
        this.currencyList = this.alphabetizeCurrencyList(response);

        this.filteredCurrencyList = this.formControl.valueChanges.pipe(
          tap(value => this.isCurrencySelected.emit(
            this.currencyList.some(e => e.name.toLowerCase() === value.toLowerCase()))
          ),
          startWith(''),
          map(value => this.filterCurrencyListByName(value || ''))
        );
      })
    ).subscribe();
  }

  onCurrencySelected(event: MatAutocompleteSelectedEvent): void {
    this.currencySelectedEvent.emit(event);
  }

  private alphabetizeCurrencyList(list: Currency[]): Currency[] {
    return list.sort((a, b) => {
      return (a.name > b.name) ? 1 : -1;
    });
  }

  private filterCurrencyListByName(value: string): Currency[] {
    const filterValue = value.toLowerCase();
    return this.currencyList.filter(currency => currency.name.toLowerCase().includes(filterValue));
  }

}
