package ru.jo4j.todoList.model;

import java.util.List;

public interface Store {
    List<Task> getTasks();

    void addTask(Task task);

    void removeTask(Task task);
}
