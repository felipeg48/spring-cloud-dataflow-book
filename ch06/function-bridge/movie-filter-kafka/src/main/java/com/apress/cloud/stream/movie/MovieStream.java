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
