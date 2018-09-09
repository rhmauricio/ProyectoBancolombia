import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const apiUrl = 'https://kmmamrl7hg.execute-api.us-east-1.amazonaws.com/dev/users/create';

@Injectable({
  providedIn: 'root'
})
export class AwsLambdaService {

  constructor(
    private http: HttpClient
  ) { }

  getCall(token: string): Observable<any> {

    const headers = new HttpHeaders().set('Authorization', token);

    return this.http.get(apiUrl, {headers: headers});
  }
}
