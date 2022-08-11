package com.leandroyabut.todolist.exception;

public class ToDoListNotFoundException extends NotFoundException {
    public ToDoListNotFoundException() {
        super("To-do list does not exist.");
    }
}
