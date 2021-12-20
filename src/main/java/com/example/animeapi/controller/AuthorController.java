package com.example.animeapi.controller;

import com.example.animeapi.domain.dto.ListResponseAll;
import com.example.animeapi.domain.model.projection.ProjectionAuthor;
import com.example.animeapi.domain.model.projection.ProjectionAuthorDetallado;
import com.example.animeapi.repository.AuthorRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllAuthors(){
        List<ProjectionAuthor> llistaAuthors = authorRepository.findBy();
        return ResponseEntity.ok().body(ListResponseAll.getResult(llistaAuthors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAuthor(@PathVariable UUID id){
        ProjectionAuthorDetallado author = authorRepository.findByAuthorid(id, ProjectionAuthorDetallado.class);
        return ResponseEntity.ok().body(author);
    }
}
