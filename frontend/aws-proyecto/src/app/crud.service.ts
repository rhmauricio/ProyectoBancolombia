import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserParameters} from './model/user-parameters.model';
import {map} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const baseUrl = 'http://localhost:8050/Users/';


@Injectable({
  providedIn: 'root'
})
export class CrudService {

  constructor(
    private http: HttpClient
  ) { }

  getAllUsers(): Observable<any> {
    return this.http.get(baseUrl + 'getAll');
  }

  getUserById(username: string): Observable<any> {
    return this.http.get<any>(baseUrl + 'get', {
      params: {
        id: username
      }
    });
  }

  updateUser(userData: any): Observable<any> {
    const header = {
      type: 'Request Login',
      id: '2018-2787'
    };

    const body = {
      header: header,
      userData: userData
    };

    return this.http.put(baseUrl + 'update', body, httpOptions);
  }
}
