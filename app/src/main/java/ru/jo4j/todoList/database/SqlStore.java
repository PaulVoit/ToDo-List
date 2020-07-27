package ru.jo4j.todoList.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import ru.jo4j.todoList.model.IStore;
import ru.jo4j.todoList.model.Task;

import static ru.jo4j.todoList.database.ToDoDbSchema.*;

public class SqlStore implements IStore {
    private SQLiteDatabase store;
    private Context context;
    private List<Task> tasks = new ArrayList<>();

    public SqlStore(Context context) {
        this.context = context;
        store = new TaskDbHelper(this.context).getWritableDatabase();
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<Task>();
        Cursor cursor = this.store.query(
                ToDoDbSchema.TodoTable.TITLE,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tasks.add(new Task(
                    cursor.getInt(cursor.getColumnIndex(ToDoDbSchema.TodoTable.Cols.ID)),
                    cursor.getString(cursor.getColumnIndex(ToDoDbSchema.TodoTable.Cols.NAME)),
                    cursor.getString(cursor.getColumnIndex(ToDoDbSchema.TodoTable.Cols.DESC)),
                    new Date(cursor.getLong(cursor.getColumnIndex(ToDoDbSchema.TodoTable.Cols.CREATED)))
            ));
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    private static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TodoTable.Cols.NAME, task.getName());
        values.put(TodoTable.Cols.DESC, task.getDesc());
        values.put(TodoTable.Cols.CREATED, task.getCreated().getTime());
        return values;
    }

    @Override
    public void addTask(Task task) {
        ContentValues values = getContentValues(task);
        store.insert(TodoTable.TITLE, null, values);
    }

    @Override
    public void removeTask(Task task) {
        store.delete(TodoTable.TITLE, "id = ?", new String[]{String.valueOf(task.getId())});
    }

    @Override
    public void editTask(Task task) {
        ContentValues values = getContentValues(task);
        store.update(TodoTable.TITLE, values, "id = ?", new String[]{String.valueOf(task.getId())});
    }


    @Override
    public List<Task> getFilteredTasks(String filter) {
        List<Task> list = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(filter.toLowerCase()) ||
                    task.getDesc().toLowerCase().contains(filter.toLowerCase())) {
                list.add(task);
            }
        }
        return list;
    }
}
