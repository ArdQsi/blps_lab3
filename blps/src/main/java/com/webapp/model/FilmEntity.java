package com.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String year;
    private String description;
    private String path;
    private Boolean subscription;
    private String token;

    //@JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name="film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private Set<GenreEntity> genres = new HashSet<GenreEntity>();

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_history",
            joinColumns = @JoinColumn(name="film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName = "id")
    )
    private Set<UserEntity> filmUser = new HashSet<UserEntity>();

}
