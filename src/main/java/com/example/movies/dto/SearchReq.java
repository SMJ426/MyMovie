package com.example.movies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class SearchReq {

    private String query;
    private final String language="ko-KR";
    public SearchReq(String query){

        this.query=query;
    }
}
