package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.DTO.*;
import com.openclassrooms.mddapi.model.DBTheme;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.services.JWTService;
import com.openclassrooms.mddapi.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public JWTService jwtService;

    @Autowired
    public IUserService userService;

    @PostMapping("/login")
    public TokenDTO login(@RequestBody @Validated UserDTO userDTO) {
        return this.userService.login(userDTO);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDTO register(@RequestBody @Validated RegisterDTO registerDTO) {
        return this.userService.register(registerDTO);
    }

    @GetMapping("/me")
    public UserDTO getUser(Principal principal) {
        final String email = principal.getName();
        return this.userService.getUserByEmail(email);
    }

    @GetMapping("/{id}")
    public List<DBTheme> getUserThemes(@PathVariable Integer id) {
        return this.userService.getThemesByUserId(id);
    }

    @PutMapping("/updateUser/{id}")
    public UpdateUserResponseDTO updateUser(@PathVariable Integer id, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateUser(id, updateUserDTO);
    }
}
