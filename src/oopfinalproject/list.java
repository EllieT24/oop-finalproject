package oopfinalproject;

import java.util.*;

public class List {
	static private int counter = 0;
	private String title;
	private String description;
	private int id;
	static private Vector<Task> tasks = new Vector<>();
	
	public List(String title, String description, String taskString) {
		this.title = title;
		this.description = description;
		parseTasks(taskString);
		this.id = counter;
		id++;
		
		
	}
	
	//create parsing function
	private void parseTasks(String tasksString) {
		String[] temp = tasksString.split(",");
		for(String s: temp) {
			Task t = new Task(s);
			tasks.add(t);
		}
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	public Vector<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task t) {
		tasks.add(t);
	}
	
//	public void deleteTask()
	
	
}
