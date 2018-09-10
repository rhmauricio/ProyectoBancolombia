import {Component, Input, OnInit} from '@angular/core';
import {AwsLambdaService} from '../aws-lambda.service';
import {ShareDataService} from '../share-data.service';

@Component({
  selector: 'app-lambda-page',
  templateUrl: './lambda-page.component.html',
  styleUrls: ['./lambda-page.component.scss']
})
export class LambdaPageComponent implements OnInit {

  @Input() token: string;

  response: any;

  constructor(
    private aws_lambda: AwsLambdaService,
    private share: ShareDataService
  ) { }

  ngOnInit() {
  }

  lambdaGetCall() {
    this.share.loaderOpen = true;
    this.aws_lambda.getCall(this.token)
      .subscribe(
        result => {
          this.share.loaderOpen = false;
          console.log(result);
          this.response = result;
        },
        error => {
          this.share.loaderOpen = false;
          this.response = 'No está autorizado para llamar la función Lambda';
        }
      );
  }



}
