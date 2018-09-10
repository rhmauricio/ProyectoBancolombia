import {AfterViewChecked, ChangeDetectorRef, Component} from '@angular/core';
import {ShareDataService} from './share-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewChecked {

  constructor(
    public share: ShareDataService,
    private cdRef: ChangeDetectorRef
  ) {}

  getLoader() {
    return this.share.loaderOpen;
  }

  ngAfterViewChecked(): void {
    this.cdRef.detectChanges();
  }

}
