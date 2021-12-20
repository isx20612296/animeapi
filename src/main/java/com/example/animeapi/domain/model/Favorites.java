package com.example.animeapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="favorite")
public class Favorites {
    @Id
    public UUID userid;
    public UUID animeid;
}