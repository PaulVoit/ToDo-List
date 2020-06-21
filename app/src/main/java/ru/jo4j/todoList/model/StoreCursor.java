package ru.jo4j.todoList.model;

import android.database.AbstractCursor;

class StoreCursor extends AbstractCursor {
    private final Store store;
    private final String selection;

    public StoreCursor(Store store, String selection) {
        this.store = store;
        this.selection = selection;
    }

    @Override
    public int getCount() {
        return store.getTasks().size();
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"_ID", "NAME"};
    }

    @Override
    public String getString(int column) {
        Task task = store.getTasks().get(getPosition());
        String value = null;
        if (column == 1) {
            if (task.getName() != null && task.getName().contains(selection)) {
                value = task.getName();
            }
        }
        return value;
    }

    @Override
    public short getShort(int column) {
        return 0;
    }

    @Override
    public int getInt(int column) {
        return 0;
    }

    @Override
    public long getLong(int column) {
        return 0;
    }

    @Override
    public float getFloat(int column) {
        return 0;
    }

    @Override
    public double getDouble(int column) {
        return 0;
    }

    @Override
    public boolean isNull(int column) {
        return false;
    }
}
