package com.apress.cloud.stream.movie;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RestController
public class MovieController {

    private StreamBridge streamBridge;

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void toMovieSupplier(@RequestBody Movie movie) {
        log.info("Sending: {} ", movie);
        assert streamBridge != null;

        // You need to set the property: spring.cloud.stream.source=movie
        // This will create the 'movie-out-0' binding
        streamBridge.send("movie-out-0", movie);
    }


}
