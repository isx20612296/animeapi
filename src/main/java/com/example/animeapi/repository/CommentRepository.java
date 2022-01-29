package com.example.animeapi.repository;

import com.example.animeapi.domain.model.Comment;
import com.example.animeapi.domain.model.projection.ProjectionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Comment getByCommentid(UUID id);

    List<ProjectionComment> getByAnimeid(UUID id);

}
