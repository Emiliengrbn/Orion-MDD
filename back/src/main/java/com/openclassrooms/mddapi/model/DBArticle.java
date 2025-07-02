package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class DBArticle extends AuditTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    @ManyToOne
    @JoinColumn(name="theme_id", referencedColumnName = "id")
    private DBTheme theme;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DBTheme getTheme() {
        return theme;
    }

    public void setTheme(DBTheme theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
