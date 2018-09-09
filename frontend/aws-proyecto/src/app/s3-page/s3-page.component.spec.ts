import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { S3PageComponent } from './s3-page.component';

describe('S3PageComponent', () => {
  let component: S3PageComponent;
  let fixture: ComponentFixture<S3PageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ S3PageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(S3PageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
