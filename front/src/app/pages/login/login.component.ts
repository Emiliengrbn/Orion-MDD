import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
    password: ['', [Validators.required, Validators.min(3)]]
  });
  
  public onError = false;

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
      error => this.onError = true
    );
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
