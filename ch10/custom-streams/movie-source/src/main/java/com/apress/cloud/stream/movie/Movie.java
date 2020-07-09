package com.apress.cloud.stream.movie;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonRootName(value = "movies")
public class Movie {
    private String id;
    private String title;
    private String actor;
    private int year;
    private String genre;
    private int stars;
}
