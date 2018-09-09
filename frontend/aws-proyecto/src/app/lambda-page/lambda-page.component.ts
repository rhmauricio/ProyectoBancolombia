import {Component, Input, OnInit} from '@angular/core';
import {AwsLambdaService} from '../aws-lambda.service';

@Component({
  selector: 'app-lambda-page',
  templateUrl: './lambda-page.component.html',
  styleUrls: ['./lambda-page.component.scss']
})
export class LambdaPageComponent implements OnInit {

  @Input() token: string;

  response: any;

  constructor(
    private aws_lambda: AwsLambdaService
  ) { }

  ngOnInit() {
  }

  lambdaGetCall() {
    this.aws_lambda.getCall(this.token)
      .subscribe(
        result => {
          console.log(result);
          this.response = result;
        }
      );
  }

}
