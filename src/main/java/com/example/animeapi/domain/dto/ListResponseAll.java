package com.example.animeapi.domain.dto;

import java.util.List;

public class ListResponseAll {
    public List<?> result;

    public ListResponseAll(List<?> result){
        this.result = result;
    }

    public static ListResponseAll getResult(List<?> result){
        return new ListResponseAll(result);
    }
}
