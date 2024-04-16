package com.example.filmLoader.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface FilmRepository {
    void delete(String token);
    String save(String token, MultipartFile file);
}
