package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.DBTheme;
import com.openclassrooms.mddapi.repository.DBThemeRepository;
import com.openclassrooms.mddapi.services.interfaces.IThemeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.repository.DBUserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ThemeService implements IThemeService {

    @Autowired
    private DBThemeRepository themeRepository;

    @Autowired
    private DBUserRepository userRepository;

    @Override
    public List<DBTheme> getAllThemes() {
        return this.themeRepository.findAll();
    }

    @Override
    public List<ThemeDTO> getAllThemesWithSubscriptionStatus(String email) {
        List<DBTheme> allThemes = this.themeRepository.findAll();
        DBUser user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        Set<Integer> subscribedThemeIds = user.getSubscriptions().stream()
                .map(DBTheme::getId)
                .collect(Collectors.toSet());
        return allThemes.stream()
                .map(theme -> new ThemeDTO(
                        theme.getId(),
                        theme.getTitle(),
                        theme.getContent(),
                        subscribedThemeIds.contains(theme.getId())
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void subsription(Integer id, String email) {
        DBUser user = this.userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        DBTheme theme = this.themeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Thème non trouvé"));
        user.addSubscription(theme);
        this.userRepository.save(user);
    }

    @Override
    public void unSubsription(Integer id, String email) {
        DBUser user = this.userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        Set<DBTheme> themes = user.getSubscriptions();
        boolean removed = themes.removeIf(theme -> theme.getId().equals(id));
        user.setSubscriptions(themes);
        this.userRepository.save(user);
    }

    @Override
    public List<ThemeDTO> getSubscribedThemes(String email) {
        List<ThemeDTO> themes = this.getAllThemesWithSubscriptionStatus(email);
        return themes.stream()
                .filter(theme -> Boolean.TRUE.equals(theme.getSubscribed()))
                .collect(Collectors.toList());
    }


    public ThemeService(DBThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }
}
