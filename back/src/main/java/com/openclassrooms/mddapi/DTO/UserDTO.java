package com.openclassrooms.mddapi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UserDTO {

    private Integer id;
    private String username;
    @Email()
    @NotNull()
    @NotEmpty()
    private String email;
    @NotEmpty()
    @NotNull()
    private String password;
    List<ThemeDTO> themes;

    public List<ThemeDTO> getThemes() {
        return themes;
    }

    public void setThemes(List<ThemeDTO> themes) {
        this.themes = themes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
