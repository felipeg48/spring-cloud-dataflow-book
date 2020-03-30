package com.apress.cloud.stream.movie;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Log4j2
@EnableBinding(Sink.class)
public class MovieStream {

    @StreamListener(Sink.INPUT)
    public void process(Movie movie){
        log.info("Movie processed: {}",movie);
    }
}
