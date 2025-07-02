package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.model.DBTheme;

import java.util.List;

public interface IThemeService {
    List<DBTheme> getAllThemes();
    List<ThemeDTO> getAllThemesWithSubscriptionStatus(String email);

    void subsription(Integer id, String email);

    void unSubsription(Integer id, String email);

    List<ThemeDTO> getSubscribedThemes(String email);
}
