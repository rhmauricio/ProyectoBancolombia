import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserParameters} from './model/user-parameters.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const baseUrl = 'http://localhost:8090/Authentication/';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient) {

  }

  loginRequest(username: string, password: string): Observable<any> {
    const header = {
      type: 'Request Login',
      id: '2018-2787'
    };

    const credentials = {
      user: username,
      password: password
    };

    const bodyRequest = {
      'credentials': credentials,
      'header': header
    };

    console.log(bodyRequest);

    return this.http.post<any>(baseUrl + 'login', bodyRequest, httpOptions);
  }

  registerRequest(userParameters: UserParameters): Observable<any> {
    const header = {
      type: 'Request Register',
      id: '2018-2787'
    };

    const body = {
      header: header,
      userParameters: userParameters
    };

    return this.http.post<UserParameters>(baseUrl + 'register', body, httpOptions);
  }

  verifyCode(userName: string, verificationCode: string): Observable<any> {
    const header = {
      type: 'Verify code',
      id: '2018-2787'
    };

    const body = {
      header: header,
      userName: userName,
      verificationCode: verificationCode
    };

    return this.http.post<any>(baseUrl + 'verification', body, httpOptions);
  }

  resendCode(userName: string): Observable<any> {
    const header = {
      type: 'Verify code',
      id: '2018-2787'
    };

    const body = {
      header: header,
      userName: userName,
      verificationCode: ''
    };

    return this.http.post<any>(baseUrl + 'resend-code', body, httpOptions);
  }
}
