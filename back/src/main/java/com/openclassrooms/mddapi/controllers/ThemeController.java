package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.model.DBTheme;
import com.openclassrooms.mddapi.services.interfaces.IThemeService;
import com.openclassrooms.mddapi.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {

    @Autowired
    public IThemeService themeService;

    //all themes
    /*@GetMapping()
    public List<DBTheme> getAllThemes() {
        return this.themeService.getAllThemes();
    }*/

    @GetMapping()
    public List<ThemeDTO> getThemesWithSubscription(Principal principal) {
        return themeService.getAllThemesWithSubscriptionStatus(principal.getName());
    }

    @GetMapping("/subscribed")
    public List<ThemeDTO> getSubscribedThemes(Principal principal) {
        return themeService.getSubscribedThemes(principal.getName());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/subscription/{id}")
    public void getSubscription(Principal principal, @PathVariable Integer id) {
        this.themeService.subsription(id, principal.getName());
    }

    @PostMapping("/unSubscription/{id}")
    public void unSubscription(Principal principal, @PathVariable Integer id) {
        this.themeService.unSubsription(id, principal.getName());
    }



}
