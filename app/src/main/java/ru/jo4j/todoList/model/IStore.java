package ru.jo4j.todoList.model;

import java.util.List;

public interface IStore {
    List<Task> getTasks();

    void addTask(Task task);

    void removeTask(Task task);

    void editTask(Task task);

    List<Task> getFilteredTasks(String filter);
}
