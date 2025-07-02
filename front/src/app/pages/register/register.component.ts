import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthSuccess, RegisterRequest } from 'src/app/interfaces/auth';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  public onError = false;
  public registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });

  constructor(private fb: FormBuilder, private router: Router, private authService : AuthService, private sessionService: SessionService) {
    
  }

  onSubmit() {
    const registerRequest = this.registerForm.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/articles'])
        });
      },
      error => this.onError = true
    );
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
