package oopfinalproject;

public class Task {
	String taskName;
    boolean completed;

    public Task(String taskName, boolean completed) {
        this.taskName = taskName;
        this.completed = completed;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return taskName + ": " + (completed ? "Completed" : "Not Completed");
    }
}