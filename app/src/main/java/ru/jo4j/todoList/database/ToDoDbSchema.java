package ru.jo4j.todoList.database;

public class ToDoDbSchema {
    public static final class TodoTable {
        public static final String TITLE = "todo_list";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String DESC = "description";
            public static final String CREATED = "created";
        }
    }
}
