package pcx.modules;
import org.openqa.selenium.By;

import core.components.ComponentHandler;
public class LogOutModule {
	private By logoutArrow = By.cssSelector("[id='am-1363']");
	private By logoutButton = By.cssSelector("[id='am-1365']");
	
	public void logOut() {
		ComponentHandler.Click(logoutArrow);
		ComponentHandler.Click(logoutButton);
		ComponentHandler.Print("Logout Successful=");
	}
	
	
}
