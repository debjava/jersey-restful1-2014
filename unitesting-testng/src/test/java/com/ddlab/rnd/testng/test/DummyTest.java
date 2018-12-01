package com.ddlab.rnd.testng.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ddlab.rnd.testng.core.Dummy;

@Test
public class DummyTest {
	
	private Dummy dummy;
	
	@BeforeTest
	public void init() {
		
		dummy = new Dummy();
	}
	
	@Test
	public void test1() {
		String s1 = dummy.getValue("Deba");
		assert(s1.equals("Deba"));
		System.out.println("************************* Test Passed ******************");
	}
	
	@Test
	public void test2() {
		String s1 = dummy.getValue("Deba1");
		assert(s1.equals("Deba1"));
		System.out.println("************************* Test Failed ******************");
	}

}
