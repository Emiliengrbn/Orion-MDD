import { Component, OnInit } from '@angular/core';

interface Article {
  title: string;
  date: string;
  author: string;
  content: string;
}

@Component({
  selector: 'app-article',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [
    {
      title: "Titre de l'article",
      date: "08/05/2025",
      author: "Auteur 1",
      content: "Content: lorem ipsum is simply dummy text of the printing and typesetting industry...Content: lorem ipsum is simply dummy text of the printing and typesetting industry...Content: lorem ipsum is simply dummy text of the printing and typesetting industry...Content: lorem ipsum is simply dummy text of the printing and typesetting industry...Content: lorem ipsum is simply dummy text of the printing and typesetting industry...Content: lorem ipsum is simply dummy text of the printing and typesetting industry...Content: lorem ipsum is simply dummy text of the printing and typesetting industry..."
    },
    {
      title: "Titre de l'article",
      date: "07/05/2025",
      author: "Auteur 2",
      content: "Content: lorem ipsum is simply dummy text of the printing and typesetting industry..."
    },
    // Ajoute d'autres articles si besoin
  ];

  ngOnInit() {
    // Quand ton API sera prête, remplace ce code par un appel HTTP :
    // this.articleService.getArticles().subscribe(data => this.articles = data);
  }
}
