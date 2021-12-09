package com.example.animeapi.domain.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID animeid;

    public String name;
    public String description;
    public String type;
    public String year;
    public String image;

    @ManyToMany
    @JoinTable(name = "authors_anime", joinColumns = @JoinColumn(name = "animeid"), inverseJoinColumns = @JoinColumn(name = "authorid"))
    public Set<Author> authors;

    @ManyToMany
    @JoinTable(name = "genres_anime", joinColumns = @JoinColumn(name = "animeid"), inverseJoinColumns = @JoinColumn(name = "genreid"))
    public Set<Genre> genres;

    public Anime(String name, String description, String type, String year, String image) {
        animeid = new UUID(0, 99999999);
        this.name = name;
        this.description = description;
        this.type = type;
        this.year = year;
        this.image = image;
    }

    public Anime(){};
}

