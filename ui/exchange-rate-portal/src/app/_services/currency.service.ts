import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_BASE_PATH } from '../_utils/constants';
import { Observable } from 'rxjs';
import { Currency } from '../_models/currency';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  private currencyBaseUrl: string = `${API_BASE_PATH}/currency`

  constructor(private http: HttpClient) {
  }

  getCurrencyList(): Observable<Currency[]> {
    return this.http.get<Currency[]>(this.currencyBaseUrl);
  }
}
