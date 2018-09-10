import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {

  loaderOpen: boolean;

  private nameSource = new BehaviorSubject('');

  private tokenSource = new BehaviorSubject('');

  constructor() { }

  get username() {
    return this.nameSource.asObservable();
  }

  get token() {
    return this.tokenSource.asObservable();
  }

  changeUsername(username: string) {
    this.nameSource.next(username);
  }

  changeToken(token: string) {
    this.tokenSource.next(token);
  }
}
