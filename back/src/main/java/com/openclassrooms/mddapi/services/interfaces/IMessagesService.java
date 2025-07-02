package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.DTO.MessageDTO;
import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface IMessagesService {
    MessageResponseDTO send(@Valid MessageDTO messageDTO);
    List<MessageDTO> getMessageForAnArticle(Integer articleId);
}
