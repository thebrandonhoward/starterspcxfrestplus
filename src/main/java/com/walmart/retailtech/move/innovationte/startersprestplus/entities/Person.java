package com.walmart.retailtech.move.innovationte.startersprestplus.entities;

public class Person
{
	private int id;
	private String location;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format( "{id=%s,location=%s,name=%s}", id, location, name );
	}
	
	

} 