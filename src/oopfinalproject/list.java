package oopfinalproject;

import java.util.ArrayList;
import java.util.List;

public class List {
	String title;
    String description;
    List<Task> tasks;

    public List(String title, String description) {
        this.title = title;
        this.description = description;
        this.tasks = new ArrayList<>();
    }

    // Add a task to the list
    public void addTask(Task task) {
        tasks.add(task);
    }
    public String getTitle() {
		return this.title;
	}
    public String getDescription() {
		return this.description;
	}
    // Get all tasks in the list
    public List<Task> getTasks() {
        return tasks;
    }
    
    public void addTasks(List<String> taskNames) {
        for (String taskName : taskNames) {
            tasks.add(new Task(taskName, false)); // Initially, tasks are not completed
        }
    }
    public String getTasksAsString() {
        StringBuilder taskListString = new StringBuilder();
        
        for (Task task : tasks) {
            taskListString.append(task.getTaskName()).append(", ");
        }
        
        // Remove the last comma and space if there's at least one task
        if (taskListString.length() > 0) {
            taskListString.setLength(taskListString.length() - 2);
        }
        
        return taskListString.toString();
    }

    // Mark a task as completed
    public void markTaskCompleted(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                task.markCompleted();
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ToDoList: ").append(title).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Tasks:\n");
        for (Task task : tasks) {
            sb.append("- ").append(task).append("\n");
        }
        return sb.toString();
    }
}