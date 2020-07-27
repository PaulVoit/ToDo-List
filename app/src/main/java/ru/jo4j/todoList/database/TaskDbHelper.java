package ru.jo4j.todoList.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class TaskDbHelper extends SQLiteOpenHelper {
    public static final String DB = "tasks.db";
    public static final int VERSION = 1;

    public TaskDbHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ToDoDbSchema.TodoTable.TITLE
                    + " (" + " id integer primary key autoincrement, "
                    + ToDoDbSchema.TodoTable.Cols.NAME + " TEXT , "
                    + ToDoDbSchema.TodoTable.Cols.DESC + " TEXT , "
                    + ToDoDbSchema.TodoTable.Cols.CREATED + " LONG);";

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
