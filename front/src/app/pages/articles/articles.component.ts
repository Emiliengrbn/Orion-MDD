import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Article } from 'src/app/interfaces/articles';
import { ArticlesService } from 'src/app/services/articles.service';

@Component({
  selector: 'app-article',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  currentSortLabel = '---'; // Label par défaut

  public articles: Article[] = []

  constructor(private articleService: ArticlesService,  private router: Router) {}

  ngOnInit() {
    // Quand ton API sera prête, remplace ce code par un appel HTTP :
    // this.articleService.getArticles().subscribe(data => this.articles = data);
    this.articleService.getAllArticles().subscribe(
              (response: Article[]) => {
                console.log(response);
                
                this.articles = response
              },
              error => new Error)
  }

  sortArticles(criteria: string): void {
    switch (criteria) {
      case 'dateAsc':
        this.articles.sort((a, b) => new Date(a.created_at).getTime() - new Date(b.created_at).getTime());
        this.currentSortLabel = 'Date croissante';
        break;
      case 'dateDesc':
        this.articles.sort((a, b) => new Date(b.created_at).getTime() - new Date(a.created_at).getTime());
        this.currentSortLabel = 'Date décroissante';
        break;
      case 'author':
        this.articles.sort((a, b) => a.author.localeCompare(b.author));
        this.currentSortLabel = 'Auteur (A → Z)';
        break;
    }
  }

  viewArticle(id: number) {
    this.router.navigate([`/articles/${id}`])
  }

}
