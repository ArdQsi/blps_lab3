package com.webapp.dto;

import lombok.Data;
import java.util.Set;

@Data
public class RequestFilmAddDto {
    private String name;
    private String year;
    private String description;
    private Boolean subscription;
    private Set<String> genres;
}