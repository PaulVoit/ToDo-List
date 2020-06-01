package ru.jo4j.todoList.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.jo4j.todoList.R;

public class EditDetailActivity extends AppCompatActivity {
    TextView name;
    TextView desc;
    int requestCode;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_task);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);

        String strName = getIntent().getStringExtra("name");
        String strDesc = getIntent().getStringExtra("desc");
        requestCode = getIntent().getIntExtra("requestCode", 0);
        position = getIntent().getIntExtra("position", 0);

        if (strName != null && strDesc != null) {
            name.setText(strName);
            desc.setText(strDesc);
        }
    }

    private void saveChanges() {

        Intent intent = new Intent();

        if (requestCode == MainActivity.EDIT_TASK) {
            intent.putExtra("position", position);
        }

        intent.putExtra("editedName", name.getText().toString());
        intent.putExtra("editDesc", desc.getText().toString());

        setResult(RESULT_OK, intent);
    }

    @Override
    public void finish() {
        saveChanges();
        super.finish();
    }
}
