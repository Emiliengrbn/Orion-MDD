import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { NewArticleComponent } from './pages/new-article/new-article.component';
import { MeComponent } from './pages/me/me.component';
import { ArticleWithIdComponent } from './pages/article-with-id/article-with-id.component';
import { AuthGuard } from './auth.guard';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [ 
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'articles', component: ArticlesComponent, canActivate: [AuthGuard]  },
  { path: 'articles/:id', component: ArticleWithIdComponent, canActivate: [AuthGuard]  },
  { path: 'themes', component: ThemesComponent, canActivate: [AuthGuard]  },
  { path: 'newArticle', component: NewArticleComponent, canActivate: [AuthGuard]  },
  { path: 'me', component: MeComponent, canActivate: [AuthGuard]  },
  { path: '*', redirectTo: 'articles', pathMatch: 'full' }
 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
