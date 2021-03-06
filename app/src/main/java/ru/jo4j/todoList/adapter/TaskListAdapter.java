package ru.jo4j.todoList.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.jo4j.todoList.ConfirmDeleteFragment;
import ru.jo4j.todoList.activities.DetailActivity;
import ru.jo4j.todoList.R;
import ru.jo4j.todoList.database.SqlStore;
import ru.jo4j.todoList.model.IStore;
import ru.jo4j.todoList.model.Task;
import ru.jo4j.todoList.model.TaskStore;


public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskHolder> implements View.OnClickListener,
        ConfirmDeleteFragment.ConfirmHintDialogListener {
    private AppCompatActivity activity;
    private IStore sqlStore;

    public TaskListAdapter(AppCompatActivity activity, IStore store) {
        this.activity = activity;
        this.sqlStore = store;
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
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
        name.setText(sqlStore.getTasks().get(position).getName());
        TextView desc = holder.itemView.findViewById(R.id.desc);
        desc.setText(sqlStore.getTasks().get(position).getDesc());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra("name", sqlStore.getTasks().get(position).getName());
            intent.putExtra("desc", sqlStore.getTasks().get(position).getDesc());
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
        return sqlStore.getTasks().size();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, DetailActivity.class);
        int position = 0;
        intent.putExtra("name", sqlStore.getTasks().get(position).getName());
        intent.putExtra("desc", sqlStore.getTasks().get(position).getDesc());
        activity.startActivity(intent);
    }

    @Override
    public void onPositiveDialogClick(int position) {
        sqlStore.getTasks().remove(position);
        notifyDataSetChanged();
    }
/*
    public List<Task> getTasks() {
      return sqlStore.getTasks();
    }
    public void addTask(Task task) {
        store.getTasks().add(task);
    }

    */
}
