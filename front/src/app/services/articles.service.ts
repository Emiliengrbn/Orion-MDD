import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../interfaces/articles';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  private pathService = 'api/articles';

  constructor(private httpClient: HttpClient) { }
  
    public getAllArticles(): Observable<any> {
        return this.httpClient.get<any>(`${this.pathService}`);
    }
  
    public getArticleById(id: string): Observable<any> {
        return this.httpClient.get<any>(`${this.pathService}/${id}`);
    }
  
    public createArticle(article: Article): Observable<any> {
        return this.httpClient.post<any>(`${this.pathService}`, article);
    }

    public addComment(comment: any): Observable<any> {
        return this.httpClient.post<any>(`api/messages`, comment);
    }

    public getCommentForAnArticle(articleId: string): Observable<any> {
        return this.httpClient.get<any>(`api/messages/${articleId}`);

    }


}
