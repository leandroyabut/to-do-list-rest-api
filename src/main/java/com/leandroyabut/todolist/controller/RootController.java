package com.leandroyabut.todolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RootController {

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "healthy!";
    }

}
