import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DynamoPageComponent } from './dynamo-page.component';

describe('DynamoPageComponent', () => {
  let component: DynamoPageComponent;
  let fixture: ComponentFixture<DynamoPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DynamoPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DynamoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
