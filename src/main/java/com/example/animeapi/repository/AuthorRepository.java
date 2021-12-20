package com.example.animeapi.repository;

import com.example.animeapi.domain.model.Author;
import com.example.animeapi.domain.model.projection.ProjectionAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<ProjectionAuthor> findBy();

    <T> T findByAuthorid(UUID id, Class<T> type);
}
