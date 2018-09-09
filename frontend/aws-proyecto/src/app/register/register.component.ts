import { Component, OnInit } from '@angular/core';
import {UserParameters} from '../model/user-parameters.model';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  userParameters: UserParameters;
  errorMsg: string;
  modalTitle: string;

  hasError: boolean;
  openModal: boolean;

  constructor(
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {
    this.userParameters = new UserParameters();
    this.userParameters.role = 'lambda';
    this.hasError = false;
  }

  selectChanged(event: any) {
    this.userParameters.role = event.target.value;
    console.log(this.userParameters);
  }

  onSubmit() {
    this.authentication.registerRequest(this.userParameters)
      .subscribe(
        success => {
          console.log(success);
          // Open modal
          this.openModal = true;
          this.modalTitle = 'Registro exitoso. Se ha enviado un código de verificación a su correo';
        },

        error => {
          console.log(error.error.errors[0]);
          const _error = error.error.errors[0];

          if (_error.status === '409') {
            this.hasError = true;

            /* Error: usuario ya existe */
            if (_error.code === '0001') {
              this.errorMsg = 'El email que intenta registrar ya está en uso';
            }

            /* Error: password débil */
            if (_error.code === '0002') {
              this.errorMsg = 'El password es débil';
            }
          }
        }
      );
  }

  onFileChange(event) {
    let reader = new FileReader();

    if(event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = result => {
        console.log(result);
      };
    }
  }
}
