package com.webapp.dto;

import com.webapp.model.GenreEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class FilmDto {
    private String name;
    private String year;
    private String description;
    private Boolean subscription;
    private String token;
    private Set<String> genres;
}
