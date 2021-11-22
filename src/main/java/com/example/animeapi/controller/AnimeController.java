package com.example.animeapi.controller;

import com.example.animeapi.domain.Anime;
import com.example.animeapi.repository.AnimeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public List<Anime> findAllAnimes() {
        return animeRepository.findAll();
    }

    @PostMapping("/")
    public Anime createAnime(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }

    //TODO(get i delete, treballar amb les ids)

    @GetMapping("/{animeid}")
    public ResponseEntity<ObjectMapper> getAnime(@PathVariable UUID animeid) {
        Anime anime = animeRepository.findById(animeid).orElse(null);

        if (anime == null) return ResponseEntity.notFound().build();

        Anime animeResp = new Anime();
        animeResp.name = "A";
        animeResp.description ="B";
        animeResp.year = "1";
        animeResp.type = "C";
        animeResp.image = "D";

        ObjectMapper resp = new ObjectMapper();
        try {
            resp.writeValueAsString(animeResp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(resp);
    }

}
