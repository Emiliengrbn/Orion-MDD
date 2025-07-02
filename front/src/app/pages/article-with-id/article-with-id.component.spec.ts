import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleWithIdComponent } from './article-with-id.component';

describe('ArticleWithIdComponent', () => {
  let component: ArticleWithIdComponent;
  let fixture: ComponentFixture<ArticleWithIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticleWithIdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleWithIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
