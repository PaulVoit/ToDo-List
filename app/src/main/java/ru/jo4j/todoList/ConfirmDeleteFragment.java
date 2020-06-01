package ru.jo4j.todoList;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmDeleteFragment extends DialogFragment {
    private ConfirmHintDialogListener callback;
    private int position;

    public interface ConfirmHintDialogListener {

        void onPositiveDialogClick(int position);
    }

    public ConfirmDeleteFragment(ConfirmHintDialogListener callback, int position) {
        this.callback = callback;
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage("Delete task?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onPositiveDialogClick(position);
                    }
                }).create();
        return dialog;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

}
