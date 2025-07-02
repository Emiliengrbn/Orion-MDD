import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Theme } from 'src/app/interfaces/themes';
import { ArticlesService } from 'src/app/services/articles.service';
import { ThemesService } from 'src/app/services/themes.service';

@Component({
  selector: 'app-new-article',
  templateUrl: './new-article.component.html',
  styleUrls: ['./new-article.component.scss']
})
export class NewArticleComponent implements OnInit {
  articleForm: FormGroup;
  themes:Theme[] = []

  constructor(private fb: FormBuilder, private router: Router, private articleService: ArticlesService, private themeService: ThemesService) {
    this.articleForm = this.fb.group({
      themeId: ['', Validators.required],
      title: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  ngOnInit(): void {
        this.themeService.getAllThemes().subscribe(
              (response: Theme[]) => {
                console.log(response);
                
                this.themes = response
              },
              error => new Error
            );
  }
  
  onSubmit() {
    if (this.articleForm.valid) {
      // Traiter la connexion ici
      console.log(this.articleForm.value);
      this.articleService.createArticle(this.articleForm.value).subscribe(
                    (response: string) => {
                      console.log(response);
                      this.router.navigate(['/articles'])
                    },
                    error => new Error)
    }
  }

  goBack() {
    this.router.navigate(['/articles']);
  }

}
