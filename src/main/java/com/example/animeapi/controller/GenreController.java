package com.example.animeapi.controller;

import com.example.animeapi.domain.dto.ListResponseAll;
import com.example.animeapi.domain.model.projection.ProjectionGenre;
import com.example.animeapi.domain.model.projection.ProjectionGenre2;
import com.example.animeapi.repository.GenreRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllGenres(){
        List<ProjectionGenre> llistaGenres = genreRepository.findBy();
        return ResponseEntity.ok().body(ListResponseAll.getResult(llistaGenres));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllGenres(@PathVariable UUID id){
        ProjectionGenre2 genre = genreRepository.findByGenreid(id, ProjectionGenre2.class);
        return ResponseEntity.ok().body(genre);
    }
}
