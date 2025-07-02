import { Component, OnInit } from '@angular/core';
import { ThemesService } from 'src/app/services/themes.service';
import { Theme } from 'src/app/interfaces/themes'

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {
  public themes: Theme[] = [];

  constructor (private themesService: ThemesService) { }

  ngOnInit(): void {
    this.themesService.getAllThemes().subscribe(
          (response: Theme[]) => {
            console.log(response);
            
            this.themes = response
          },
          error => new Error
        );
  }

  toggleSubscription(theme: Theme) {
    // theme.subscribed = !theme.subscribed;
    if (theme.subscribed === true) {
      this.themesService.unSubscribe(theme.id).subscribe()
      theme.subscribed = false
    } else if (theme.subscribed === false) {
      this.themesService.subscribe(theme.id).subscribe()
      theme.subscribed = true
    }
  }
}
