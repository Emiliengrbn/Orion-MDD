import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ThemesService {

  private pathService = 'api/themes';

  constructor(private httpClient: HttpClient) { }

  public getAllThemes(): Observable<any> {
      return this.httpClient.get<any>(`${this.pathService}`);
  }

  public subscribe(id: number): Observable<any> {
    return this.httpClient.post<any>(`${this.pathService}/subscription/${id}`, null)
  }

  public unSubscribe(id: number): Observable<any> {
    return this.httpClient.post<any>(`${this.pathService}/unSubscription/${id}`, null)
  }

  public getSubscribedThemes(): Observable<any> {
    return this.httpClient.get<any>(`${this.pathService}/subscribed`)
  }
}
