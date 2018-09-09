import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../authentication.service';
import {Router} from '@angular/router';
import {ShareDataService} from '../share-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  verificationCode: string;
  errorMsg: string;
  modalTitle: string;

  title: string;

  isLoginActive: boolean;
  hasError: boolean;
  openModal: boolean;

  constructor(
    private authentication: AuthenticationService,
    private share: ShareDataService,
    private router: Router
  ) { }

  ngOnInit() {
    this.title = 'Bienvenido al demo de AWS';
    this.isLoginActive = true;
    this.hasError = false;
    this.openModal = false;
  }

  onSubmit() {
    this.authentication.loginRequest(this.username, this.password)
      .subscribe(
        result => {
          console.log(result);
          this.share.changeUsername(this.username);
          this.router.navigateByUrl('/dashboard');
        },
        error => {
          console.log(error.error.errors[0]);
          const _error = error.error.errors[0];

          /* Error de credenciales */
          if (_error.status === '401') {
            this.hasError = true;
            this.errorMsg = _error.detail;
            return;
          }

          /* Error: usuario no verificado */
          if (_error.status === '409') {
            this.isLoginActive = false;
            this.title = 'Verificar el código de registro';
          }
        }
      );

  }

  onSendCode() {
    this.authentication.verifyCode(this.username, this.verificationCode)
      .subscribe(
        sucess => {
          // Open modal
          this.openModal = true;
          this.modalTitle = 'El código de registro se ha verificado';
          this.hasError = false;
        },

        error => {
          console.log(error.error.errors[0]);
          const _error = error.error.errors[0];

          if (_error.status === '409') {
            this.hasError = true;

            if (_error.code === '0001') {
              this.errorMsg = _error.detail;
              return;
            }

            if (_error.code === '0002') {
              this.errorMsg = _error.detail;
            }
          }
        }
      );
  }

  onResendCode() {
    this.authentication.resendCode(this.username)
      .subscribe(
        success => {
          // Open modal
          this.openModal = true;
          this.modalTitle = 'El código de registro se ha enviado a su correo';
          this.hasError = false;
        },

        error => {
          console.log(error.error.errors[0]);
        }
      );
  }
}
