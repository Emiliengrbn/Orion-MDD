import { Component } from '@angular/core';

interface Theme {
  id: number;
  title: string;
  description: string;
  subscribed: boolean;
}

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent {
  themes: Theme[] = [
    {
      id: 1,
      title: 'Titre du thème',
      description: 'Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard...',
      subscribed: false
    },
    {
      id: 2,
      title: 'Titre du thème',
      description: 'Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard...',
      subscribed: false
    },
    {
      id: 3,
      title: 'Titre du thème',
      description: 'Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard...',
      subscribed: true
    },
    {
      id: 4,
      title: 'Titre du thème',
      description: 'Description: lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard...',
      subscribed: true
    }
    // Ajoute d'autres thèmes si besoin
  ];

  toggleSubscription(theme: Theme) {
    theme.subscribed = !theme.subscribed;
    // Quand ton API sera prête, fais ici un appel HTTP :
    // if (theme.subscribed) {
    //   this.themeService.subscribe(theme.id).subscribe();
    // } else {
    //   this.themeService.unsubscribe(theme.id).subscribe();
    // }
  }
}
