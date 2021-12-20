package com.example.animeapi.domain.model.projection;

import com.example.animeapi.domain.model.Anime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"authorid", "name", "image"})
public interface ProjectionAuthor {
    UUID getAuthorid();
    String getName();
    String getImage();

    @JsonIgnoreProperties({"authors","genres"})
    Set<ProjectionAnimeSimple> getAnimes();

}
