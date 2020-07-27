package ru.jo4j.todoList.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import java.nio.file.FileStore;
import java.util.Date;

import ru.jo4j.todoList.R;
import ru.jo4j.todoList.adapter.TaskListAdapter;
import ru.jo4j.todoList.database.SqlStore;
import ru.jo4j.todoList.database.ToDoDbSchema;
import ru.jo4j.todoList.model.IStore;
import ru.jo4j.todoList.model.Task;
import ru.jo4j.todoList.model.TaskStore;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final int NEW_TASK = 111;
    public static final int EDIT_TASK = 112;
    private TaskListAdapter adapter;
    private SqlStore store;
    private RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        store = new SqlStore(this);
        Button newTask = findViewById(R.id.btnNewTask);
        newTask.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditDetailActivity.class);
            intent.putExtra("requestCode", NEW_TASK);
            startActivityForResult(intent, NEW_TASK);
        });
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_icon).getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        store.getFilteredTasks(newText);
        adapter = new TaskListAdapter(this, store);
        recycler.setAdapter(adapter);
        return true;
    }

    private void updateUI() {
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskListAdapter(this, store);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (EDIT_TASK):
                int id = 0;
                if (data != null) {
                    id = data.getIntExtra("id", 0);
                }
                String strName = data.getStringExtra("editedName");
                String strDesc = data.getStringExtra("editDesc");
                store.editTask(new Task(id, strName, strDesc, null));
                //  adapter.getTasks().get(position).setName(strName);
                // adapter.getTasks().get(position).setDesc(strDesc);
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
                // adapter.addTask(task);
                ContentValues value = new ContentValues();
                value.put(ToDoDbSchema.TodoTable.Cols.NAME, data.getStringExtra("editedName"));
                value.put(ToDoDbSchema.TodoTable.Cols.DESC, data.getStringExtra("editDesc"));
                value.put(ToDoDbSchema.TodoTable.Cols.CREATED, (new Date()).getTime());
                store.addTask(task);
                adapter.notifyDataSetChanged();
        }
    }
}
