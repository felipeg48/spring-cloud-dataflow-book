package com.apress.cloud.stream.movie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class MovieStream {
    String GENRE_SCIENCE_FICTION = "science-fiction";

    @Bean
    public Function<Flux<Movie>, Flux<Movie>> onlyScienceFiction() {
        return flux -> flux.filter( movie -> movie.getGenre().equals(GENRE_SCIENCE_FICTION));
    }
}


//Using Function Composition
//You need to enable the spring.cloud.function.definition property to have one or more functions to create a composition
/*
@Configuration
public class MovieStream {
    String GENRE_SCIENCE_FICTION = "science-fiction";

    @Bean
    public Function<Flux<Movie>, Flux<Movie>> onlyScienceFiction() {
        return flux -> flux.filter( movie -> movie.getGenre().equals(GENRE_SCIENCE_FICTION));
    }

    @Bean
    public Function<Flux<Movie>, Flux<Movie>> titleUpperCase() {
        return flux -> flux.map( movie -> {
            movie.setTitle(movie.getTitle().toUpperCase());
            return movie;
        });
    }
}
*/