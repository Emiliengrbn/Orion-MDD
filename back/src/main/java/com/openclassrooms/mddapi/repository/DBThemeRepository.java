package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.DBTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBThemeRepository extends JpaRepository<DBTheme, Integer> {
}
