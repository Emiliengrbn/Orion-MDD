import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthSuccess, LoginRequest } from 'src/app/interfaces/auth';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  public loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, this.passwordValidator]]
  });
  
  public onError = false;
  public errorMessage: string = ''

  constructor(private fb: FormBuilder, private router: Router, private authService : AuthService, private sessionService: SessionService) {
  }

  submit() {
    const loginRequest = this.loginForm.value as LoginRequest;
    this.authService.login(loginRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/articles'])
        });
        this.router.navigate(['/articles'])
      },
      error => {
        this.onError = true
        this.errorMessage = error.error.message
        
      }
    );
  }

  passwordValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;

    if (!value) {
      return null; // le validateur required gère ça
    }

    const errors = [];

    if (value.length < 8) {
      errors.push('au moins 8 caractères');
    }

    if (!/[0-9]/.test(value)) {
      errors.push('au moins un chiffre');
    }

    if (!/[!@#$%^&*(),.?":{}|<>]/.test(value)) {
      errors.push('au moins un caractère spécial');
    }

    return errors.length
      ? { passwordInvalid: `Le mot de passe doit contenir ${errors.join(', ')}.` }
      : null;
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
