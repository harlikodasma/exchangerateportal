import { Injectable } from '@angular/core';
import { API_BASE_PATH } from '../_utils/constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ExchangeRate } from '../_models/exchange-rate';

@Injectable({
  providedIn: 'root'
})
export class ExchangeRateService {

  private exchangeRateBaseUrl: string = `${API_BASE_PATH}/exchange-rate`;

  constructor(private http: HttpClient) {
  }

  getExchangeRateHistoryForCurrency(currencyAlphabeticCode: string): Observable<ExchangeRate[]> {
    return this.http.get<ExchangeRate[]>(`${this.exchangeRateBaseUrl}?currencyAlphabeticCode=${currencyAlphabeticCode}`);
  }
}
