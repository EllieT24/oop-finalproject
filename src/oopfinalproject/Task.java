package oopfinalproject;

import java.io.Serializable;

public class Task implements Serializable {
	static private int counter = 0;
	private int id;
	private String name;
	private boolean complete;
	
	public Task(String name) {
		this.name = name;
		this.id = counter;
		this.complete = false;
		counter++;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean getComplete() {
		return this.complete;
	}
	
	public void setComplete(boolean c) {
		this.complete = c;
	}
}
