package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.DTO.ArticleDTO;
import com.openclassrooms.mddapi.model.DBArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBArticleRepository  extends JpaRepository<DBArticle, Integer> {
    List<DBArticle> findByThemeId(Integer themeId);
}
