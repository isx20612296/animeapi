package com.example.animeapi.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"animeid", "name", "image"})
public interface ProjectionAnimeSimple {
    UUID getAnimeid();
    String getName();
    String getImage();
}
