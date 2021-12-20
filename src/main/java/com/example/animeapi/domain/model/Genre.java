package com.example.animeapi.domain.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID genreid;

    public String label;

    @ManyToMany(mappedBy = "genres")
    Set<Anime> animes;


}
