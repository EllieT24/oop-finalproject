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
    
    public List<Task> getTasks() {
        return tasks;
    }
    
    public void addTasks(List<String> taskNames) {
        for (String taskName : taskNames) {
        	if (!taskName.contains("_")) {
        		Task t = new Task(taskName, false);
            	tasks.add(t); 
            	
        	} else {
	        	String[] s = taskName.split("_", 2);
	        	String name = s[0].trim();
	        	String completed = s[1].trim(); 
	        	Task t = new Task(name, completed.equals("true") ? true : false);
	        	tasks.add(t); 
	        	System.out.println(t.isCompleted());
	//          tasks.add(new Task(taskName, false)); 
        	}
        }
    }
    
    
    public String getTasksAsString() {
        StringBuilder taskListString = new StringBuilder();
        
        for (Task task : tasks) {
 
            taskListString.append(task.getTaskName());
            taskListString.append("_");
            
            taskListString.append(task.isCompleted() ? "true" : "false");
            taskListString.append(", ");
        }
        
        
        if (taskListString.length() > 0) {
            taskListString.setLength(taskListString.length() - 2);
        }
        
        return taskListString.toString();
    }

    public void markTaskCompleted(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                task.markCompleted();	// then mark the task as completed
                break;
            }
        }
    }
    
    public double taskCompleteCounter(){
    	double count = 0;
    	for(Task t: this.tasks) {
    		if(t.isCompleted()) {
    			count++;
    		}
    	}
    	return count;
    }
    
    public double taskCounter() {
    	double count = 0;
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