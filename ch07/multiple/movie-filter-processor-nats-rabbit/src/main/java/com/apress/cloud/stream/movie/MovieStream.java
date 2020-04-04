package com.apress.cloud.stream.movie;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/*
@Configuration
public class MovieStream {

    String GENRE_DRAMA = "drama";

    @Bean
    public Function<Flux<Movie>, Flux<Movie>> onlyDrama() {
        return flux -> flux.filter( movie -> movie.getGenre().equals(GENRE_DRAMA));
    }

}
*/

@EnableBinding(Processor.class)
public class MovieStream {

    String GENRE_DRAMA = "drama";

    @Filter(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public boolean onlyDrama(Movie movie) {
        return movie.getGenre().equals(GENRE_DRAMA);
    }

}
