package ru.jo4j.todoList.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.jo4j.todoList.R;
import ru.jo4j.todoList.adapter.TaskListAdapter;
import ru.jo4j.todoList.model.Store;
import ru.jo4j.todoList.model.Task;
import ru.jo4j.todoList.model.TaskStore;

public class MainActivity extends AppCompatActivity {
    private Button newTask;
    Store tasks = new TaskStore();
    public static final int NEW_TASK = 111;
    public static final int EDIT_TASK = 112;
    TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newTask = findViewById(R.id.btnNewTask);
        newTask.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditDetailActivity.class);
            intent.putExtra("requestCode", NEW_TASK);
            startActivityForResult(intent, NEW_TASK);
        });
        updateUI();
    }

    private void updateUI() {

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        TaskListAdapter taskListAdapter = new TaskListAdapter(tasks.getTasks(), this);
        recycler.setAdapter(taskListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case (EDIT_TASK):
                int position = data.getIntExtra("position", 0);
                String strName = data.getStringExtra("editedName");
                String strDesc = data.getStringExtra("editDesc");
                taskListAdapter.getTasks().get(position).setName(strName);
                taskListAdapter.getTasks().get(position).setDesc(strDesc);
               taskListAdapter.notifyDataSetChanged();
                break;

            case(NEW_TASK):
                Task task = new Task(
                        data.getStringExtra("editedName"),
                        data.getStringExtra("editDesc"),
                        new Date());
                taskListAdapter.addTask(task);
                taskListAdapter.notifyDataSetChanged();
        }
    }
}
