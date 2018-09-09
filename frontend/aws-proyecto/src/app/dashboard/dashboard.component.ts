import {Component, OnInit} from '@angular/core';
import {ShareDataService} from '../share-data.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  username: string;
  token: string;

  lambdaPage: boolean;
  dynamoPage: boolean;
  s3Page: boolean;

  constructor(
    private share: ShareDataService,
    private router: Router
  ) { }

  ngOnInit() {
    this.lambdaPage = false;
    this.dynamoPage = false;
    this.s3Page = false;

    this.share.username.subscribe(username => this.username = username);
    this.share.token.subscribe(token => this.token = token);

    if (this.username === '') {
      this.router.navigateByUrl('/login');
    }

    console.log(this.token);
  }

  onLambdaClick() {
    this.lambdaPage = true;
  }

  onS3Click() {
    this.s3Page = true;
  }

  onDynamoClick() {
    this.dynamoPage = true;
  }

  cardsVisibles() {
    return !this.lambdaPage &&
      !this.dynamoPage &&
      !this.s3Page;
  }

}
