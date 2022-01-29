package com.example.animeapi.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"commentid","userid", "message"})
public interface ProjectionComment {
    UUID getCommentid();
    UUID getUserid();
    String getMessage();
}
