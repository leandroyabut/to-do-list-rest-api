package com.leandroyabut.todolist.service;

import com.leandroyabut.todolist.exception.NotFoundException;
import com.leandroyabut.todolist.exception.ToDoListNotFoundException;
import com.leandroyabut.todolist.model.Task;
import com.leandroyabut.todolist.model.ToDoList;
import com.leandroyabut.todolist.repository.TaskRepository;
import com.leandroyabut.todolist.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoListService {

    private final TaskRepository taskRepository;
    private final ToDoListRepository toDoListRepository;

    public List<ToDoList> getToDoLists() {
        return toDoListRepository.findAll();
    }

    public ToDoList getToDoListById(int id) {
        return toDoListRepository.findById(id)
                .orElseThrow(ToDoListNotFoundException::new);
    }

    public ToDoList getToDoListById(int id, boolean showCompleted, boolean showHighPriority) {
        ToDoList toDoList = getToDoListById(id);
        List<Task> tasks = toDoList.getTasks().stream()
                .filter(showCompleted ? Task::isCompleted : task -> true)
                .filter(showHighPriority ? Task::isHighPriority : task -> true)
                .collect(Collectors.toList());
        toDoList.setTasks(tasks);
        return toDoList;
    }

    public Task getTaskByToDoListId(int listId, int taskId) {
        ToDoList toDoList = getToDoListById(listId);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task does not exist in this to-do list."));
        if (!Objects.equals(task.getToDoList().getId(), toDoList.getId()))
            throw new NotFoundException("Task does not exist in this to-do list.");
        return task;
    }

    @Transactional
    public ToDoList saveToDoList(ToDoList toDoList) {
        ToDoList savedToDoList = toDoListRepository.save(toDoList);
        taskRepository.saveAll(toDoList.getTasks().stream()
                .peek(task -> task.setToDoList(savedToDoList))
                .collect(Collectors.toList()));
        taskRepository.deleteAll(toDoList.getTasks().stream().filter(Task::isDeleted)
                .collect(Collectors.toList()));
        List<Task> filtered = toDoList.getTasks().stream()
                .filter(task -> !task.isDeleted())
                .collect(Collectors.toList());
        toDoList.setTasks(filtered);
        return toDoListRepository.save(toDoList);
    }

    public void deleteToDoList(int id) {
        toDoListRepository.deleteById(id);
    }

}
