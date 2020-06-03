package ru.jo4j.todoList.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.jo4j.todoList.R;

public class DetailActivity extends AppCompatActivity {

    TextView name;
    TextView desc;
    int requestCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        String strName = getIntent().getStringExtra("name");
        String strDesc = getIntent().getStringExtra("desc");
        requestCode = getIntent().getIntExtra("requestCode", 0);
        if (strName != null && strDesc != null) {
            name.setText(strName);
            desc.setText(strDesc);
        }
    }
}
