package com.example.animeapi.domain.model.projection;

import com.example.animeapi.domain.model.Anime;

import java.util.Set;
import java.util.UUID;

public interface UserResponse {
    UUID getUserid();
    String getUsername();
    Set<Anime> getFavorited();
}
