package com.leandroyabut.todolist.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpErrorException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
