package com.example.animeapi.controller;

import com.example.animeapi.domain.dto.FavoriteRequest;
import com.example.animeapi.domain.dto.UserRegisterRequest;
import com.example.animeapi.domain.model.Anime;
import com.example.animeapi.domain.model.Favorite;
import com.example.animeapi.domain.model.User;
import com.example.animeapi.domain.dto.ListResponseAll;
import com.example.animeapi.domain.dto.MessageResponse;
import com.example.animeapi.domain.model.projection.ProjectionAnimeSimple;
import com.example.animeapi.domain.model.projection.UserResponse;
import com.example.animeapi.repository.FavoriteRepository;
import com.example.animeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder passwordEncoder;

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

    @GetMapping("/favorites")
    public ResponseEntity<?> getUserFavorite (Authentication authentication) {
        User authenticatedUser;
        if (authentication != null){
            authenticatedUser =  userRepository.findByUsername(authentication.getName());
            if (authenticatedUser != null) {
                return ResponseEntity.ok().body(userRepository.findByUsername(authentication.getName(), UserResponse.class));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageResponse.getMessage("No autorizado"));
    }

    @PostMapping(path = "/register" )
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest requestUserRegister) {

        if (userRepository.findByUsername(requestUserRegister.username) == null) {
            User user = new User();
            user.username = requestUserRegister.username;
            user.password = passwordEncoder.encode(requestUserRegister.password);
            return ResponseEntity.ok().body(userRepository.save(user).userid.toString());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(MessageResponse.getMessage("Nom d'usuari no disponible"));
    }

    @PostMapping("/favorites")
    public ResponseEntity<?> postUserFavorite (@RequestBody FavoriteRequest favoriteRequest, Authentication authentication) {
        Favorite favorite = new Favorite();
        favorite.animeid = favoriteRequest.animeid;
        favorite.userid = userRepository.findByUsername(authentication.getName()).userid;
        return ResponseEntity.ok().body(favoriteRepository.save(favorite));
    }

    //@DeleteMapping("/{id}/favorites")
    //public ResponseEntity<?> deleteUserFavorites (@PathVariable)

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
