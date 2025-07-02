import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Theme } from 'src/app/interfaces/themes';
import { AuthService } from 'src/app/services/auth.service';
import { SessionService } from 'src/app/services/session.service';
import { ThemesService } from 'src/app/services/themes.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit{

  public themes: Theme[] = []

  public profileForm = this.fb.group({
    username: [''],
    email: [''],
    password: ['']
  });
  
  constructor(private fb: FormBuilder, private themesService: ThemesService, private userService: AuthService, private sessionService: SessionService, private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.loadThemes()
  }

  loadThemes(): void {
    this.themesService.getSubscribedThemes().subscribe(
          (response: Theme[]) => {
            console.log(response);
            
            this.themes = response
          },
          error => new Error)
  }

  saveProfile() {
    console.log('Profil sauvegardé:', this.profileForm.value);
    const formValues = this.profileForm.value;
    const userId = this.sessionService.user?.id;

    if (!userId) {
      console.error('Utilisateur non connecté ou ID introuvable');
      return;
    }

    // Construire un objet ne contenant que les champs non vides
    type FormKeys = 'username' | 'email' | 'password';

    const filteredValues: Partial<{ username: string | null; email: string | null; password: string | null }> = {};

    (Object.keys(formValues) as FormKeys[]).forEach(key => {
      const value = formValues[key];
      if (value != null && value.toString().trim() !== '') {
        filteredValues[key] = value;
      }
    });

    // Appelle ton service avec filteredValues
    this.userService.updateUser(userId, filteredValues).subscribe(response => {
      console.log('Profil mis à jour', response);
      this.profileForm.reset();
      this.snackBar.open('Utilisateur bien sauvegardé !', 'Fermer', {
      duration: 3000,
    });
    }, error => {
      console.error('Erreur lors de la mise à jour', error);
    });
  }

  unsubscribe(theme: Theme) {
    console.log('Désabonnement de :', theme.title);
      this.themesService.unSubscribe(theme.id).subscribe(() => {
        this.loadThemes()
      })
  }

}
