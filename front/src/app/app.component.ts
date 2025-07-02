import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter, Observable } from 'rxjs';
import { AuthService } from './services/auth.service';
import { SessionService } from './services/session.service';
import { User } from './interfaces/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  showHeader = true;

  constructor(private router: Router, private authService : AuthService, private sessionService: SessionService) {
    this.router.events
      .pipe(
        filter(event => event instanceof NavigationEnd)
      )
      .subscribe((event) => {
        const navigation = event as NavigationEnd;
        const hiddenRoutes = ['/'];
        this.showHeader = !hiddenRoutes.includes(navigation.urlAfterRedirects);
      });
  }

  ngOnInit(): void {
    this.autoLog()
  }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  get addPadding(): boolean {
    return this.showHeader;
  }

  
  public autoLog(): void {
    this.authService.me().subscribe(
      (user: User) => {
        this.sessionService.logIn(user);
      },
      (_) => {
        this.sessionService.logOut();
      }
    )
  }
}
