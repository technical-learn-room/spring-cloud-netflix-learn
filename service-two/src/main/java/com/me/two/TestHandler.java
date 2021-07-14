package com.me.two;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TestHandler {

    public Mono<ServerResponse> testHandler(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request.bodyToMono(TestRequest.class).flatMap(req ->
                        Mono.just(new TestResponse(req.getFirstName() + req.getLastName(),
                                req.getAge(),
                                String.format("%d%d%d",
                                        req.getGrade(),
                                        req.getClassroom(),
                                        req.getNumber())
                        ))
                ), TestResponse.class);
    }
}
