import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Article } from 'src/app/interfaces/articles';
import { ArticlesService } from 'src/app/services/articles.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-article-with-id',
  templateUrl: './article-with-id.component.html',
  styleUrls: ['./article-with-id.component.scss']
})
export class ArticleWithIdComponent implements OnInit {
  
  article!: Article
  messages!: any
  newComment: string = '';
  articleId = this.route.snapshot.paramMap.get('id')!;

  constructor(private route: ActivatedRoute, private router: Router, private articleService: ArticlesService, private sessionService: SessionService) {}

  ngOnInit(): void {
    console.log('ID de l’article récupéré depuis l’URL :', this.articleId);
    this.articleService.getArticleById(this.articleId).subscribe(
                  (response: Article) => {
                    console.log(response);
                    
                    this.article = response
                  },
                  error => new Error)

    this.loadComments()
                  
  }

  goBack() {
    this.router.navigate(['/articles']);
  }

  loadComments() {
    this.articleService.getCommentForAnArticle(this.articleId).subscribe(
      (response: any) => {
        console.log(response)

        this.messages = response
      },
                  error => new Error
    )
  }

  sendComment() {
    const userId = this.sessionService.user?.id;
    console.log(userId);
    const aze = {
      userId: userId,
      articleId: this.articleId,
      content: this.newComment
    }
    if (this.newComment.trim()) {
      console.log(aze);
      
      this.articleService.addComment(aze).subscribe(
                  (response: string) => {
                    console.log(response);
                    this.loadComments()
                  },
                  error => new Error)
      // Logique d'envoi à l'API ou au service ici
      this.newComment = '';
    }
  }
}
