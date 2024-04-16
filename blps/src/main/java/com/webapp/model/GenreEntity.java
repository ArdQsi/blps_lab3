package com.webapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="genre")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    //@JsonBackReference
    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    private Set<FilmEntity> films = new HashSet<FilmEntity>();

    @Override
    public String toString() {
        return name;
    }
}
