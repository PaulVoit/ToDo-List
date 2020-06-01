package ru.jo4j.todoList.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.jo4j.todoList.ConfirmDeleteFragment;
import ru.jo4j.todoList.activities.DetailActivity;
import ru.jo4j.todoList.R;
import ru.jo4j.todoList.activities.EditDetailActivity;
import ru.jo4j.todoList.model.Task;

import static ru.jo4j.todoList.activities.MainActivity.EDIT_TASK;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskHolder> implements View.OnClickListener,
          ConfirmDeleteFragment.ConfirmHintDialogListener {
    private AppCompatActivity activity;
    private List<Task> tasks;

    public TaskListAdapter(List<Task> tasks, AppCompatActivity activity) {
        this.activity = activity;
        this.tasks = tasks;
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.TaskHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.name);
        name.setText(tasks.get(position).getName());
        TextView desc = holder.itemView.findViewById(R.id.desc);
        desc.setText(tasks.get(position).getDesc());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra("name", tasks.get(position).getName());
            intent.putExtra("desc", tasks.get(position).getDesc());
            activity.startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(view -> {
            DialogFragment dialog = new ConfirmDeleteFragment(TaskListAdapter.this, position);
            dialog.show(activity.getSupportFragmentManager(), "dialog_tag");
            return false;
        });

    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, DetailActivity.class);
        int position = 0;
        intent.putExtra("name", tasks.get(position).getName());
        intent.putExtra("desc", tasks.get(position).getDesc());
        activity.startActivity(intent);
    }

    @Override
    public void onPositiveDialogClick(int position) {
        tasks.remove(position);
        notifyDataSetChanged();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
