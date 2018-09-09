import { TestBed, inject } from '@angular/core/testing';

import { AwsLambdaService } from './aws-lambda.service';

describe('AwsLambdaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AwsLambdaService]
    });
  });

  it('should be created', inject([AwsLambdaService], (service: AwsLambdaService) => {
    expect(service).toBeTruthy();
  }));
});
