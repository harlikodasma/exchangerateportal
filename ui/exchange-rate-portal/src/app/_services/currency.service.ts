import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_BASE_PATH } from '../_utils/constants';
import { Observable } from 'rxjs';
import { Currency } from '../_models/currency';
import { CurrencyCalculate } from '../_models/currency-calculate';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  private currencyBaseUrl: string = `${API_BASE_PATH}/currency`;

  constructor(private http: HttpClient) {
  }

  getCurrencyList(): Observable<Currency[]> {
    return this.http.get<Currency[]>(this.currencyBaseUrl);
  }

  calculateCurrencyRate(amount: number, currencyAlphabeticCode: string): Observable<CurrencyCalculate> {
    return this.http.get<CurrencyCalculate>(`${this.currencyBaseUrl}/calculate?amount=${amount}&currencyAlphabeticCode=${currencyAlphabeticCode}`);
  }
}
