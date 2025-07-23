import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  sidebarOpen = false;
  isLoggedIn = false;
  
  constructor(public router: Router, private sessionService: SessionService) {
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
    console.log(this.isLoggedIn);
    
  }

  toggleSidebar() {
    this.sidebarOpen = !this.sidebarOpen;
  }


  logout() {
    this.sessionService.logOut();
    this.router.navigate(['/']);
  }

  isRouteActive(route: string): boolean {
    return this.router.url === route;
  }

}
