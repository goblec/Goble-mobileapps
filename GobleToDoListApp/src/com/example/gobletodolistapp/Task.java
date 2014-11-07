package com.example.gobletodolistapp;

public class Task {
	private int taskId;
	private boolean priority;
	private String date;
	private String task;
	private boolean isCompleted;
	
	//constructor
	public Task() {
		
	}
	//Accessors and modifiers
	public void setTaskId (int id){
		taskId = id;
	}
	public void setPriority (boolean p){
		priority = p;
	}
	public void setDate (String d){
		date = d;
	}
	public void setTask (String t){
		task = t;
	}
	public void setCompleted (boolean c){
		isCompleted = c;
	}
	
	//accesors
	public int getId (){
		 return taskId;
	}
	public boolean getPriority (){
		 return priority;
	}
	public String getDate (){
		 return date;
	}
	public String getTaskDetails(){
		 return task;
	}
	
	public boolean getCompleted (){
		 return isCompleted;
	}
	
	//String of Class
	public String toString () {
		String result = "";
		
		if (priority){
				result += "Priority:";
		}
		result += task + "" + date;
		return result;
		}
	}
	

