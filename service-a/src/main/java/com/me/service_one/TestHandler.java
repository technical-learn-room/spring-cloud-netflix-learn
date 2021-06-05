package com.me.service_one;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
@RequiredArgsConstructor
public class TestHandler {
    private ServiceTwoConnection serviceTwoConnection = new Retrofit.Builder()
            .baseUrl("http://service-two")
            .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
            .build()
            .create(ServiceTwoConnection.class);

    public Mono<ServerResponse> testHandler(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request.bodyToMono(TestRequest.class).flatMap(req -> {
                    TestResponse res = new TestResponse();
                    serviceTwoConnection.connectServiceTwo(req).enqueue(new Callback<>() {
                        @Override
                        public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {
                            TestResponse responseBody = response.body();
                            res.update(responseBody.getName(),
                                    responseBody.getAge(),
                                    responseBody.getGradeClassNumber());
                        }

                        @Override
                        public void onFailure(Call<TestResponse> call, Throwable t) {
                            throw new BusinessException("BUSINESS_ERROR", "비지니스 에러입니당.", HttpStatus.BAD_REQUEST);
                        }
                    });
                    return Mono.just(res);
                }), TestResponse.class);
    }
}
