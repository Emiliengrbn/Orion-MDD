import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isLoggedIn = false;
  
  constructor(public router: Router) {
      this.router.events
        .pipe(
          filter(event => event instanceof NavigationEnd)
        )
        .subscribe((event) => {
          const navigation = event as NavigationEnd; // ✅ Cast ici
          const hiddenRoutes = ['/', 'login', 'register'];
          this.isLoggedIn = !hiddenRoutes.includes(navigation.urlAfterRedirects);
        });
      }

  ngOnInit(): void {
  }

  isRouteActive(route: string): boolean {
    return this.router.url === route;
  }

}
