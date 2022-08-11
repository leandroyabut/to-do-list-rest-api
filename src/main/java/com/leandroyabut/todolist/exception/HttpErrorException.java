package com.leandroyabut.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class HttpErrorException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
}
