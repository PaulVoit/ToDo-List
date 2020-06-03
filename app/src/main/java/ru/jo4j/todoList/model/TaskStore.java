package ru.jo4j.todoList.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TaskStore implements Store {
    private List<Task> tasks;


    public TaskStore() {
        tasks = new ArrayList<>();
        tasks.add(new Task("Сделать уроки", "сделать уроки вовремя", new Date(123412341234134L)));
        tasks.add(new Task("Сделать генеральную уборку", "тщательно убрать помещение", new Date(123412371234134L)));
        tasks.add(new Task("Сходить в магазин", "сходить в ближайший магазин", new Date(123412541234134L)));
        tasks.add(new Task("Покормить собаку", "покормить собаку кормом", new Date(123412341034134L)));
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
    }


}
