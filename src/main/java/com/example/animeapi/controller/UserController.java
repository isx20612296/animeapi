package com.example.animeapi.controller;

import com.example.animeapi.domain.model.Anime;
import com.example.animeapi.domain.model.Favorites;
import com.example.animeapi.domain.model.User;
import com.example.animeapi.domain.dto.ListResponseAll;
import com.example.animeapi.domain.dto.MessageResponse;
import com.example.animeapi.domain.model.projection.ProjectionAnimeSimple;
import com.example.animeapi.repository.FavoriteRepository;
import com.example.animeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllUsers(){

        List<User> llistaUsers = new ArrayList<>(userRepository.findAll());
        List<UserResultat> llistaResposta = new ArrayList<>();

        for (User u : llistaUsers){
            llistaResposta.add(new UserResultat(u.userid, u.username, u.favorites));
        }

        return ResponseEntity.ok().body(ListResponseAll.getResult(llistaResposta));
    }

    @GetMapping("/{id}/favorites")
    public ResponseEntity<?> getUserFavorite (@PathVariable UUID id) {
        ProjectionAnimeSimple favs = favoriteRepository.findByUserid(id, ProjectionAnimeSimple.class);
        return new ResponseEntity<ProjectionAnimeSimple>(favs, HttpStatus.OK);
    }

    @PostMapping("/{id}/favorites")
    public ResponseEntity<?> postUserFavorite (@RequestBody Favorites favorites) {
            return ResponseEntity.ok().body(favoriteRepository.save(favorites));
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {

        User userfind = userRepository.findByUsername(userRequest.username);

        if (userfind != null) return ResponseEntity.status(HttpStatus.CONFLICT).body(MessageResponse.getMessage("Ja existeix un usuari amb el nom '"+ userRequest.username +"'"));

        userRepository.save(new User(userRequest.username, userRequest.password));

        userfind = userRepository.findByUsername(userRequest.username);
        return new ResponseEntity<UserResultat>(new UserResultat(userfind.userid, userfind.username, userfind.favorites), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteUser(@PathVariable UUID id){

        User user = userRepository.findById(id).orElse(null);

        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse.getMessage("No s'ha trobat l'usuari amd id '" + id +"'"));

        userRepository.delete(user);

        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.getMessage("S'ha eliminat l'usuari amd id '" + id + "'"));

    }

    @DeleteMapping("/")
    public ResponseEntity<? extends Object> deleteAllUsers(){
        userRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}

class UserResultat{
    public UUID userid;
    public String username;
    public Set<Anime> favorites;

    public UserResultat(UUID userid, String username, Set<Anime> favorites){
        this.userid = userid;
        this.username = username;
        this.favorites = favorites;
    }
}

class UserRequest{
    public String username;
    public String password;
}
