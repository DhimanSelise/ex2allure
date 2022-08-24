package packages.common.localrunner;

import core.components.ComponentHandler;
import pcx.modules.*;

public class JavaRunner {
	public static void main(String[] args) {
		Visit(); 
		login();  // For Login
		
		//new AdditionalModule().QualityCheckAssign("testdhiman001@yopmail.com", "420");
		
		//new AdditionalModule().StatusUpdate("testdhiman001@yopmail.com");
		CreateAndEditCase();  // This will Create and Edit a Case
		
	}
	
	public static void Visit() {
		System.out.println("testing now..."); 
		ComponentHandler.Init(); 
		ComponentHandler.NavigateToURL("http://pcx.seliselocal.com/");
		System.out.println("$ Site Visited");
	}
	
	public static void login() {
	    new LoginModule().getLogin("kawsar.ahmed@selise.ch", "en", "1qazZAQ!");
	    System.out.println("Login Worked");
	}
	
	public static void CreateAndEditCase() {
		ArrayAndString data = new CreateCase().ParashiftCreateCase(1, 1, "Back Office", "Saniuzzaman Robin");
		new EditCase().EditCaseNow(data.caseTitle, data.itemAndSubItemList);
	}
	
	

}
