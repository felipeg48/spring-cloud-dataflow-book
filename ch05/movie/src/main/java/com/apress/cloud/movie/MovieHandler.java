package com.apress.cloud.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MovieHandler {

    public void process(Message<Movie> message){
        log.info(message.getPayload().toString());
    }
}

