package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.DTO.MessageDTO;
import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.DBArticle;
import com.openclassrooms.mddapi.model.DBMessage;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.repository.DBArticleRepository;
import com.openclassrooms.mddapi.repository.DBMessagesRepository;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.services.interfaces.IMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService implements IMessagesService {

    @Autowired
    private DBMessagesRepository messageRepository;

    @Autowired
    private DBUserRepository userRepository;

    @Autowired
    private DBArticleRepository articleRepository;

    @Override
    public MessageResponseDTO send(MessageDTO messageDTO) {
        DBUser user = userRepository.findById(messageDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        DBArticle article = articleRepository.findById(messageDTO.getArticleId())
                .orElseThrow(() -> new NotFoundException("Article not found"));

        DBMessage message = new DBMessage();
        message.setContent(messageDTO.getContent());
        message.setUser(user);
        message.setArticle(article);

        messageRepository.save(message);

        return new MessageResponseDTO("Message sent with success");
    }

    @Override
    public List<MessageDTO> getMessageForAnArticle(Integer articleId) {
        List<DBMessage> messages = messageRepository.findByArticleId(articleId);

        return messages.stream().map(message -> {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(message.getId());
            messageDTO.setArticleId(message.getArticle().getId());
            messageDTO.setUserId(message.getUser().getId());
            messageDTO.setUserName(message.getUser().getUsername());
            messageDTO.setContent(message.getContent());
            return messageDTO;
        }).collect(Collectors.toList());
    }
}
