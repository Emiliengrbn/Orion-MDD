package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.DTO.*;
import com.openclassrooms.mddapi.model.DBTheme;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.services.interfaces.IJWTService;
import com.openclassrooms.mddapi.services.interfaces.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    public IJWTService jwtService;

    @Autowired
    private DBUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean isValidPassword(String password) {
        if (password == null) return false;
        // Longueur >= 8
        if (password.length() < 8) return false;
        // Regex : au moins une minuscule, une majuscule, un chiffre et un caractère spécial
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$";
        return password.matches(regex);
    }

    @Override
    public TokenDTO register(RegisterDTO registerDTO) {
        if (!isValidPassword(registerDTO.getPassword())) {
            throw new IllegalArgumentException("Mot de passe invalide : il doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial.");
        }

        final boolean isEmail = this.userRepository.existsByEmail(registerDTO.getEmail());
        if (isEmail) {
            throw new IllegalArgumentException("Utilisateur non trouvé");
        }

        DBUser user = new DBUser();

        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        String hashedPassword = passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        final String token = jwtService.generateToken(registerDTO.getEmail());
        return new TokenDTO(token);
    }

    @Override
    public TokenDTO login(UserDTO userDTO) {
        DBUser user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        boolean passwordMatches = passwordEncoder.matches(userDTO.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new IllegalArgumentException("Utilisateur non trouvé");
        }
        final String token = jwtService.generateToken(user.getEmail());
        return new TokenDTO(token);
    }

    @Override
    public UserDTO getUserByEmail(String email){
        DBUser user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        UserDTO me = new UserDTO();
        me.setEmail(user.getEmail());
        me.setId(user.getId());
        me.setUsername(user.getUsername());
        return me;
    }

    @Override
    public List<DBTheme> getThemesByUserId(Integer userId) {
        DBUser user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + userId));

        // On récupère les abonnements (Set<DBTheme>) et on le convertit en List
        Set<DBTheme> subscriptions = user.getSubscriptions();

        return subscriptions.stream().toList();
    }

    @Override
    public UpdateUserResponseDTO updateUser(Integer id, UpdateUserDTO updateUserDTO) {
        DBUser user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        if (updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().isBlank()) {
            user.setUsername(updateUserDTO.getUsername());
        }

        if (updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().isBlank()) {
            user.setEmail(updateUserDTO.getEmail());
        }

        if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isBlank()) {
            if (!isValidPassword(updateUserDTO.getPassword())) {
                throw new IllegalArgumentException("Mot de passe invalide : il doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial.");
            }
            String hashedPassword = passwordEncoder.encode(updateUserDTO.getPassword());
            user.setPassword(hashedPassword);
        }

        userRepository.save(user);

        UpdateUserResponseDTO response = new UpdateUserResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }
}
