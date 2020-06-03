package ru.jo4j.todoList.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Date;

import ru.jo4j.todoList.R;
import ru.jo4j.todoList.adapter.TaskListAdapter;
import ru.jo4j.todoList.model.Task;
import ru.jo4j.todoList.model.TaskStore;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_TASK = 111;
    public static final int EDIT_TASK = 112;
    private TaskListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newTask = findViewById(R.id.btnNewTask);
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
        TaskStore store = new TaskStore();
        adapter = new TaskListAdapter(this, store);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (EDIT_TASK):
                int position = 0;
                if (data != null) {
                    position = data.getIntExtra("position", 0);
                }
                String strName = data.getStringExtra("editedName");
                String strDesc = data.getStringExtra("editDesc");
                adapter.getTasks().get(position).setName(strName);
                adapter.getTasks().get(position).setDesc(strDesc);
                adapter.notifyDataSetChanged();
                break;
            case (NEW_TASK):
                Task task = null;
                if (data != null) {
                    task = new Task(
                            data.getStringExtra("editedName"),
                            data.getStringExtra("editDesc"),
                            new Date());
                }
                adapter.addTask(task);
                adapter.notifyDataSetChanged();
        }
    }
}
