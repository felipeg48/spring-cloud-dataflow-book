package com.apress.cloud.stream.movie;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.splitter.FileSplitter;

import java.io.File;


@EnableConfigurationProperties(MovieStreamProperties.class)
@AllArgsConstructor
@EnableBinding(Source.class)
public class MovieStream {

    private MovieStreamProperties movieStreamProperties;
    private MovieConverter movieConverter;

    @Bean
    public IntegrationFlow fileFlow(){
        return IntegrationFlows.from(Files
                        .inboundAdapter(new File(this.movieStreamProperties.getDirectory()))
                        .preventDuplicates(true)
                        .patternFilter(this.movieStreamProperties.getNamePattern()),
                e -> e.poller(Pollers.fixedDelay(5000L)))

                .split(Files.splitter().markers())
                .filter(p -> !(p instanceof FileSplitter.FileMarker))
                .transform(Transformers.converter(movieConverter))
                .transform(Transformers.toJson())
                .channel(Source.OUTPUT)
                .get();
    }

}
