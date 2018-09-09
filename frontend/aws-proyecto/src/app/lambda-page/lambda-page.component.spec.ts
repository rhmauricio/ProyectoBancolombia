import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LambdaPageComponent } from './lambda-page.component';

describe('LambdaPageComponent', () => {
  let component: LambdaPageComponent;
  let fixture: ComponentFixture<LambdaPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LambdaPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LambdaPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
