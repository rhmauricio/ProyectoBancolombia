import {Component, OnInit} from '@angular/core';
import {ShareDataService} from '../share-data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  username: string;

  constructor(
    private share: ShareDataService
  ) { }

  ngOnInit() {
    this.share.username.subscribe(username => this.username = username);
    console.log(this.username);
  }

}
