package com.example.animeapi.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"animeid","name", "description", "type", "year", "image"})
public interface ProjectionAnimeReducido3 {
    UUID getAnimeid();
    String getName();
    String getType();
    String getYear();
    String getImage();

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();
}
