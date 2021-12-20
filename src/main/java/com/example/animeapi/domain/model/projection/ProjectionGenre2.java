package com.example.animeapi.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"genreid", "label", "image"})
public interface ProjectionGenre2 {
    UUID getGenreid();
    String getLabel();

    Set<ProjectionAnimeReducido3> getAnimes();
}
