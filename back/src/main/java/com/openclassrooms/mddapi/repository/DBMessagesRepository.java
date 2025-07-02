package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.DBMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBMessagesRepository extends JpaRepository<DBMessage, Integer> {
    List<DBMessage> findByArticleId(Integer articleId);
}
