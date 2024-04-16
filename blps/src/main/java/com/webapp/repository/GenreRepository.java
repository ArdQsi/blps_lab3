package com.webapp.repository;

import com.webapp.model.FilmEntity;
import com.webapp.model.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    GenreEntity findByName(String name);

}
