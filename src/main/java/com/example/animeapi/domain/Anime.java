package com.example.animeapi.domain;

import javax.persistence.*;
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

