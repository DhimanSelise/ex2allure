package pcx.modules;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.junit.*;
import core.components.ComponentHandler;

public class Visit {
	
	public String data() {
		return "Bhai" ; 
	}
	
	@BeforeMethod
	public void Visit() {
		System.out.println("Visit Done");
	}
	@AfterMethod
	public void ends() {
		System.out.println("Exit");
	}
	
	@Test
	public void test() {
		System.out.println("Dhiman");
		System.out.println(data());
	}
	
	@Test
	public void test2() {
		System.out.println("Dhiman 2");
	}
	
	
}
