package com.me.two;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class TestRouter {

    @Bean
    public RouterFunction<?> route(TestHandler testHandler){
        return RouterFunctions.route()
                .POST("/test",
                        RequestPredicates.accept(MediaType.APPLICATION_JSON)
                            .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
                        testHandler::testHandler)
                .build();
    }
}
