package com.example.animeapi.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID commentid;

    public UUID userid;
    public UUID animeid;
    public String message;

    public Comment(){}

    public Comment(UUID userid, UUID animeid, String message) {
        this.userid = userid;
        this.animeid = animeid;
        this.message = message;
    }
}
