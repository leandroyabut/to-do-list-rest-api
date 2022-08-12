package com.leandroyabut.todolist.controller;

import com.leandroyabut.todolist.model.Task;
import com.leandroyabut.todolist.model.ToDoList;
import com.leandroyabut.todolist.service.ToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/to-do-lists")
@CrossOrigin
public class ToDoListController {

    private final ToDoListService toDoListService;

    @GetMapping
    public List<ToDoList> getToDoLists() {
        return toDoListService.getToDoLists();
    }

    @GetMapping("/{id}")
    public ToDoList getToDoListById(@PathVariable Integer id) {
        return toDoListService.getToDoListById(id);
    }

    @GetMapping("/{toDoListId}/tasks/{taskId}")
    public Task getTaskByToDoListId(@PathVariable Integer toDoListId, @PathVariable Integer taskId) {
        return toDoListService.getTaskByToDoListId(toDoListId, taskId);
    }

    @PostMapping
    public ToDoList saveToDoList(@RequestBody ToDoList toDoList) {
        return toDoListService.saveToDoList(toDoList);
    }

    @DeleteMapping("/{id}")
    public void deleteToDoList(@PathVariable Integer id) {
        toDoListService.deleteToDoList(id);
    }

}