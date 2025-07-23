package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
    Optional<DBUser> findByEmail(String email);
    Optional<DBUser> findById(Integer id);
    boolean existsByEmail(String email);
}
