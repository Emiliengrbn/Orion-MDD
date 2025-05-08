import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  showHeader = true;

  constructor(private router: Router) {
    this.router.events
      .pipe(
        filter(event => event instanceof NavigationEnd)
      )
      .subscribe((event) => {
        const navigation = event as NavigationEnd; // ✅ Cast ici
        const hiddenRoutes = ['/'];
        this.showHeader = !hiddenRoutes.includes(navigation.urlAfterRedirects);
      });
  }

  get addPadding(): boolean {
    return this.showHeader;
  }
}
