package packages.common.localrunner;

import org.junit.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.components.ComponentHandler;
import core.components.ObjectRepository;
import io.qameta.allure.Feature;
import pcx.modules.LoginModule;

public class JUnitRunner {
	
	@Feature("Dhiman Test")
    @Parameters({"42000"})
    @Test(dependsOnGroups = {"Login Bruh"})
	public void Visit() {
		System.out.println("testing now...");
		ComponentHandler.Init(); 
		ComponentHandler.NavigateToURL("http://pcx.seliselocal.com/");
		System.out.println("Site Visited");
		new LoginModule().getLogin("kawsar.ahmed@selise.ch", "en", "1qazZAQ!");
	    System.out.println("Login Worked");
	}
	

	public void login() {
	    new LoginModule().getLogin("kawsar.ahmed@selise.ch", "en", "1qazZAQ!");
	    System.out.println("Login Worked");
	}
	

	public void Quit() {
	    System.out.println("************ All Script has been ended ************");
	    ObjectRepository.driver.quit();
	}
}
