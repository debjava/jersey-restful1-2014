package com.ddlab.rnd.findbug;

import java.util.HashMap;

public class General1 {
	
	public static void show(String s) {
		try {
			System.out.println("Class App ...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getValue( String s) {
		return s.substring(10);
	}
	public static void show() {
		System.out.println("Class App ...");
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public void doIt() {
		
		HashMap map = new HashMap();
	}

}
