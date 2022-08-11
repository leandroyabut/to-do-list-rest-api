package com.leandroyabut.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private boolean completed;
    private boolean highPriority;
    private boolean deleted;

    @ManyToOne
    @JsonIgnore
    private ToDoList toDoList;

    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.highPriority = false;
    }

    public Task(String description, boolean highPriority) {
        this.description = description;
        this.completed = false;
        this.highPriority = highPriority;
    }
}
