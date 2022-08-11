package com.leandroyabut.todolist.repository;

import com.leandroyabut.todolist.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
}
