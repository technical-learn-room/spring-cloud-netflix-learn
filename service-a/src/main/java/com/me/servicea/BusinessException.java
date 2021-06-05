package com.me.servicea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException {
    private String code;
    private String message;
    private HttpStatus status;
}
