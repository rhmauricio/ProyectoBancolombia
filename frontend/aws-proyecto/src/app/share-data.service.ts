import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {

  private messageSource = new BehaviorSubject('');

  constructor() { }

  get username() {
    return this.messageSource.asObservable();
  }

  changeUsername(username: string) {
    this.messageSource.next(username);
  }
}
