package com.example.animeapi.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@IdClass(Favorite.class)
@Table(name="favorite")
public class Favorite implements Serializable{
    @Id
    public UUID userid;

    @Id
    public UUID animeid;

    public UUID getUserid(){
        return userid;
    }

    public UUID getAnimeid(){
        return animeid;
    }
}



