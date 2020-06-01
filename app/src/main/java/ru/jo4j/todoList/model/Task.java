package ru.jo4j.todoList.model;

import java.util.Date;

public class Task {
    private String name;
    private String desc;
    private Date created;
    private boolean closed = false;

    public Task(String name, String desc, Date created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (isClosed() != task.isClosed()) return false;
        if (!getName().equals(task.getName())) return false;
        if (!getDesc().equals(task.getDesc())) return false;
        return getCreated().equals(task.getCreated());
    }
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDesc().hashCode();
        result = 31 * result + getCreated().hashCode();
        result = 31 * result + (isClosed() ? 1 : 0);
        return result;
    }
}
