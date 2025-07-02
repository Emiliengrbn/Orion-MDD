import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthSuccess, LoginRequest, RegisterRequest } from '../interfaces/auth';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = 'api/auth';

  constructor(private httpClient: HttpClient) { }

  public register(registerRequest: RegisterRequest): Observable<AuthSuccess> {
    return this.httpClient.post<AuthSuccess>(`${this.pathService}/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.httpClient.post<AuthSuccess>(`${this.pathService}/login`, loginRequest);
  }

  public me(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }

  public updateUser(id: number, updateRequest: any): Observable<any> {
    return this.httpClient.put<User>(`${this.pathService}/updateUser/${id}`, updateRequest);
  }


  isLoggedIn(): boolean {
    // Exemple simple : regarde si un token existe dans le localStorage
    return !!localStorage.getItem('token');
  }
}
