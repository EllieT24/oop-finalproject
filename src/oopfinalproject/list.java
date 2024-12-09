package oopfinalproject;

import java.util.ArrayList;
import java.util.List;

public class list {
    String title;
    String description;
    List<Task> tasks;

    public list(String title, String description) {
        this.title = title;
        this.description = description;
        this.tasks = new ArrayList<>();
    }

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
            tasks.add(new Task(taskName, false)); 
        }
    }
    public String getTasksAsString() {
        StringBuilder taskListString = new StringBuilder();
        
        for (Task task : tasks) {
            taskListString.append(task.getTaskName()).append(", ");
        }
        
        if (taskListString.length() > 0) {
            taskListString.setLength(taskListString.length() - 2);
        }
        
        return taskListString.toString();
    }

    public void markTaskCompleted(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                task.markCompleted();
                break;
            }
        }
    }
    
    public int taskCompleteCounter(){
    	int count = 0;
    	for(Task t: this.tasks) {
    		if(t.isCompleted()) {
    			count++;
    		}
    	}
    	return count;
    }
    
    public int taskCounter() {
    	int count = 0;
    	for(Task t: this.tasks) {
    		count++;
    	}
    	return count;
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
