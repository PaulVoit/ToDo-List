package ru.jo4j.todoList.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskStore implements IStore {
    private static final TaskStore INST = new TaskStore();
    private List<Task> tasks;

    public static TaskStore getStore() {
        return INST;
    }

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

    @Override
    public void editTask(Task task) {

    }


    @Override
    public List<Task> getFilteredTasks(String filter) {
        List<Task> list = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getName().toLowerCase().contains(filter.toLowerCase()) ||
                    task.getDesc().toLowerCase().contains(filter.toLowerCase())) {
                list.add(task);
            }
        }
        return list;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }
}
