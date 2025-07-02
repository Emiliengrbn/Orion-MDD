package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class DBMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private DBArticle article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private DBUser user;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DBArticle getArticle() {
        return article;
    }

    public void setArticle(DBArticle article) {
        this.article = article;
    }

    public DBUser getUser() {
        return user;
    }

    public void setUser(DBUser user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
