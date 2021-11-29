package com.example.animeapi.domain.dto;

public class MessageResponse {

    public String message;

    public MessageResponse(String message){
        this.message = message;
    }

    public static MessageResponse getMessage(String message){
        return new MessageResponse(message);
    }
}
