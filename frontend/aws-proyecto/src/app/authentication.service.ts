import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient) {

  }

  loginRequest(username: string, password: string): Observable<> {
    const header = {
      type: 'Request Login',
      id: '2018-2787'
    };

    const credentials = {
      username: username,
      password: password
    };

    const bodyRequest = {
      header
    }
  }
}
