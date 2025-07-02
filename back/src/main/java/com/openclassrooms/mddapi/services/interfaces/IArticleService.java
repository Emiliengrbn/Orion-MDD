package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.DTO.ArticleDTO;
import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import com.openclassrooms.mddapi.model.DBArticle;

import java.util.List;

public interface IArticleService {
    MessageResponseDTO createArticle(ArticleDTO articleDTO, String email);
    DBArticle getArticleById(Integer id);
    List<DBArticle> getAllArticles();
}
