package com.example.animeapi.domain.model;

import com.example.animeapi.domain.model.projection.ProjectionAnimeSimple;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;

    public String username;
    public String password;

    public User(String username, String password) {
        userid = new UUID(0, 999999999);
        this.username = username;
        this.password = password;
    }

    @ManyToMany(mappedBy = "favorited")
    public Set<ProjectionAnimeSimple> favorites;

    public User(){}

    public UUID getUserId(){
        return userid;
    }
}
