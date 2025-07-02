package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.DTO.ArticleDTO;
import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import com.openclassrooms.mddapi.DTO.TokenDTO;
import com.openclassrooms.mddapi.model.DBArticle;
import com.openclassrooms.mddapi.services.interfaces.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping
    public List<DBArticle> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public DBArticle getArticleById(@PathVariable Integer id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createArticle(@RequestBody @Validated ArticleDTO articleDTO, Principal principal) {
        return this.articleService.createArticle(articleDTO, principal.getName());
    }
}
