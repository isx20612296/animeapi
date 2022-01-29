package com.example.animeapi.controller;

import com.example.animeapi.domain.dto.RequestComment;
import com.example.animeapi.domain.model.Anime;
import com.example.animeapi.domain.dto.ListResponseAll;
import com.example.animeapi.domain.dto.MessageResponse;
import com.example.animeapi.domain.model.Comment;
import com.example.animeapi.domain.model.Favorite;
import com.example.animeapi.domain.model.projection.ProjectionAnime;
import com.example.animeapi.repository.AnimeRepository;
import com.example.animeapi.repository.CommentRepository;
import com.example.animeapi.repository.FavoriteRepository;
import com.example.animeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserRegisterRequest userRegisterRequest;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllAnimes() {
        List<ProjectionAnime> llistaAnimeResposta = animeRepository.findBy();
        return ResponseEntity.ok().body(ListResponseAll.getResult(llistaAnimeResposta));
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody AnimeRequest animeRequest) {
        Anime anime = new Anime(animeRequest.name, animeRequest.description, animeRequest.type, animeRequest.year, animeRequest.image);
        Anime animeFind = animeRepository.findByName(animeRequest.name);
        if (animeFind != null) return ResponseEntity.status(HttpStatus.CONFLICT).body(MessageResponse.getMessage("Ja existeix un anime amb el nom '" + animeRequest.name +"'"));
        return ResponseEntity.status(HttpStatus.OK).body(animeRepository.save(anime));
    }

//    @PostMapping("/")
//    public Anime createAnime(@RequestBody UserRegisterRequest userRegisterRequest){
//        UserRepository userRepository;
//        if (userRepository.findB())
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnime(@PathVariable UUID id) {
        ProjectionAnime anime = animeRepository.findByAnimeid(id, ProjectionAnime.class);

        if (anime == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("No s'ha trobat l'anime amb id '" + id + "'"));

        return new ResponseEntity<ProjectionAnime>(anime, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id){

        Anime anime = animeRepository.findById(id).orElse(null);

        if (anime == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("No s'ha trobat l'anime amb id '" + id + "'"));

        animeRepository.delete(anime);

        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.getMessage("S'ha eliminat l'anime amd id '" + id + "'"));

    }

    // Comment
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable UUID id){

        if (animeRepository.findByAnimeid(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("Error: Anime no trobat a la base de dades:" + id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentRepository.getByAnimeid(id));
    }

//    @GetMapping("/{id}/comments/{idComment}")
//    public ResponseEntity<?> getComments(@PathVariable UUID id, @PathVariable UUID idComment){
//
//        if (animeRepository.findByAnimeid(id) == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("Error: Anime no trobat a la base de dades:" + id));
//        }
//
//        if (commentRepository.getByCommentid(idComment) == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("ERROR : Comentari no trobat"));
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(commentRepository.getByCommentid(idComment));
//    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> postComment(@PathVariable UUID id, @RequestBody RequestComment requestComment, Authentication authentication){

        if (authentication == null || userRepository.findByUsername(authentication.getName()) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageResponse.getMessage("ERROR: No autenticat"));
        }

        if (animeRepository.findByAnimeid(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("Error: Anime no trobat a la base de dades:" + id));
        }

        commentRepository.save(new Comment((userRepository.findByUsername(authentication.getName()).getUserId()), id, requestComment.message));
        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.getMessage("Comentari publicat correctament"));

    }

    @DeleteMapping("/{id}/comments/{idComment}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id, @PathVariable UUID idComment, Authentication authentication){

        if (authentication == null || userRepository.findByUsername(authentication.getName()) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageResponse.getMessage("ERROR : No autenticat"));
        }

        if (commentRepository.getByCommentid(idComment) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("ERROR : Comentari no trobat"));
        }
        commentRepository.deleteById(idComment);
        return ResponseEntity.status(HttpStatus.OK).body("comentari eliminat correctament");
    }

}

class AnimeRequest {
    public String name;
    public String description;
    public String type;
    public String year;
    public String image;

    public AnimeRequest(String name, String description, String type, String year, String image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.year = year;
        this.image = image;
    }
}
