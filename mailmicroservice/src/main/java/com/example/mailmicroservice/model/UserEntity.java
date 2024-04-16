package com.example.mailmicroservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private long balance = 0;
    @Email
    private String email;

    private String subscriptionEndDate;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<CardEntity> cards = new HashSet<CardEntity>();

    //@JsonBackReference
    @ManyToMany(mappedBy = "filmUser", fetch = FetchType.EAGER)
    private Set<FilmEntity> userFilm = new HashSet<FilmEntity>();

}

