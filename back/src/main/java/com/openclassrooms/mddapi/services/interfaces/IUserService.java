package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.DTO.*;
import com.openclassrooms.mddapi.model.DBTheme;
import com.openclassrooms.mddapi.model.DBUser;

import java.util.List;

public interface IUserService {
    TokenDTO register(RegisterDTO registerDTO);
    TokenDTO login(UserDTO userDTO);
    UserDTO getUserByEmail(String email);
    List<DBTheme> getThemesByUserId(Integer userId);
    UpdateUserResponseDTO updateUser(Integer id, UpdateUserDTO updateUserDTO);
}
