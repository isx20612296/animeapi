package com.example.animeapi.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"authorid", "name", "image"})
public interface ProjectionAuthorDetallado {
    UUID getAuthorid();
    String getName();
    String getImage();

    @JsonIgnoreProperties("authors")
    Set<ProjectionAnimeReducido> getAnimes();

}