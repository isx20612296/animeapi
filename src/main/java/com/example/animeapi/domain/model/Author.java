package com.example.animeapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID authorid;

    public String name;
    public String image;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    public Set<Anime> animes;

}
