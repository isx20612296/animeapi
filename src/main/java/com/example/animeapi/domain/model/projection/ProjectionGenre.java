package com.example.animeapi.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"genreid", "label"})
public interface ProjectionGenre {
    UUID getGenreid();
    String getLabel();

    Set<ProjectionAnimeReducido2> getAnimes();
}
