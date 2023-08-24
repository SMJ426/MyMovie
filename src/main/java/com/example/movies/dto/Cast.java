package com.example.movies.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cast {

    private Long cast_id;
    private String name;
    private String profile_path;
    private String character;
    private String biography;

    @Builder
    public Cast(Long cast_id,String biography, String name, String profile_path, String character) {
        this.cast_id = cast_id;
        this.name = name;
        this.profile_path = profile_path;
        this.character = character;
        this.biography=biography;
    }

}