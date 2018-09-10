import { Component, OnInit } from '@angular/core';
import {CrudService} from '../crud.service';
import {ShareDataService} from '../share-data.service';

@Component({
  selector: 'app-dynamo-page',
  templateUrl: './dynamo-page.component.html',
  styleUrls: ['./dynamo-page.component.scss']
})
export class DynamoPageComponent implements OnInit {

  users: any[];
  details: any[] = [];

  constructor(
    private crud: CrudService,
    private share: ShareDataService
  ) { }

  ngOnInit() {
    this.getAllUsers();
    /*this.users = [
      {
        username: 'Guillermo',
        lastName: 'Grajales'
      },
      {
        username: 'Mauricio',
        lastName: 'Ramirez'
      }
    ];*/
  }

  getAllUsers() {
    this.share.loaderOpen = true;
    this.crud.getAllUsers()
      .subscribe(
        result => {
          this.share.loaderOpen = false;
          console.log(result.data);
          this.users = result.data;

          for (let i = 0; i < this.users.length; i++) {
            this.details[i] = false;
          }
        },
        error => {
          this.share.loaderOpen = false;
          console.error('Error retrieving users', error);
        }
      );
  }

  getUser(username: string) {
    this.crud.getUserById(username)
      .subscribe(

      );
  }

  editUser(user: any, index: number) {
    console.log(user);
  }

  detailUser(user: any, index: number) {
    /*this.crud.getUserById(user.email)
      .subscribe(
        result => {
          console.log(result);
          this.details[index] = !this.details[index];
        }
      );*/

    this.details[index] = !this.details[index];
  }

  //updateUser()


}
