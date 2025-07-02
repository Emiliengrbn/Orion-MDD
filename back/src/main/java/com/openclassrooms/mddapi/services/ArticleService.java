package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.DTO.ArticleDTO;
import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.DTO.UserDTO;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.DBArticle;
import com.openclassrooms.mddapi.model.DBTheme;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.repository.DBArticleRepository;
import com.openclassrooms.mddapi.repository.DBThemeRepository;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.services.interfaces.IArticleService;
import com.openclassrooms.mddapi.services.interfaces.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private DBArticleRepository articleRepository;

    @Autowired
    public IUserService userService;

    @Autowired
    private DBUserRepository userRepository;

    @Autowired
    private DBThemeRepository themeRepository;

    @Override
    public MessageResponseDTO createArticle(ArticleDTO articleDTO, String email) {
        DBArticle article = new DBArticle();
        article.setContent(articleDTO.getContent());
        article.setTitle(articleDTO.getTitle());

        DBTheme theme = themeRepository.findById(articleDTO.getThemeId())
                .orElseThrow(() -> new NotFoundException("Thème non trouvé"));
        article.setTheme(theme);

        UserDTO user = this.userService.getUserByEmail(email);
        article.setAuthor(user.getUsername());

        articleRepository.save(article);

        return new MessageResponseDTO("Article created");
    }

    @Override
    public DBArticle getArticleById(Integer id) {
        DBArticle article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article non trouvé avec l'ID : " + id));

        return article;
    }

    @Override
    public List<DBArticle> getAllArticles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        DBUser user = userRepository.findByEmail(email);
        Set<Integer> subscribedThemeIds = user.getSubscriptions().stream()
                .map(DBTheme::getId)
                .collect(Collectors.toSet());
        return subscribedThemeIds.stream()
                .flatMap(themeId -> this.articleRepository.findByThemeId(themeId).stream())
                .collect(Collectors.toList());
    }
}
