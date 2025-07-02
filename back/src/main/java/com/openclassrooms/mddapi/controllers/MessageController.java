package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.DTO.MessageDTO;
import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import com.openclassrooms.mddapi.repository.DBMessagesRepository;
import com.openclassrooms.mddapi.services.interfaces.IMessagesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    DBMessagesRepository dbMessagesRepository;

    @Autowired
    public IMessagesService messagesService;

    @PostMapping("/messages")
    public MessageResponseDTO messages(@Valid @RequestBody MessageDTO messageDTO) {
        return this.messagesService.send(messageDTO);
    }

    @GetMapping("/messages/{articleId}")
    public List<MessageDTO> getMessageForAnArticle(@PathVariable Integer articleId) {
        return this.messagesService.getMessageForAnArticle(articleId);
    }
}
