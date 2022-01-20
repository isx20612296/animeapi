package com.example.animeapi.domain.model.projection;

import com.example.animeapi.domain.model.Anime;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"userid","username","favorited"})
public interface UserResponse {
    UUID getUserid();
    String getUsername();
    Set<Anime> getFavorited();
}
