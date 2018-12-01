package com.ddlab.rnd.beans;

public class Customer implements java.io.Serializable {
	
	private static final long serialVersionUID = -2474244643984032335L;
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}