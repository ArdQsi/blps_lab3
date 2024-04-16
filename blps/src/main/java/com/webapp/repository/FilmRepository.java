package com.webapp.repository;

import java.util.*;
import com.webapp.model.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    FilmEntity findFilmByName(String name);

    FilmEntity findFilmById(Long id);
    FilmEntity findFilmByToken(String token);

    List<FilmEntity> findFilmByYear(String year);

    @Transactional
    void deleteAllByToken(String token);
}
