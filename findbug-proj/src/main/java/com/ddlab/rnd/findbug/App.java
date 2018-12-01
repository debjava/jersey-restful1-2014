package com.ddlab.rnd.findbug;

import java.util.ArrayList;

public class App 
{
	private String s;
	public static void show() {
		System.out.println("Class App ...");
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    public void doIt() {
    	
    	notify();
    	
    	ArrayList list = new ArrayList();
    }
}
