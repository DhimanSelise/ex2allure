package pcx.modules;

import org.openqa.selenium.By;

import core.components.ComponentHandler;

public class LoginModule {
	// Fields of login first page
	private By inputEmail = By.cssSelector("[name='email']");
	private By inputPass = By.cssSelector("[name='password']");
	private By buttonLogin = By.cssSelector("[aria-label='LOGIN']");
	
	// After login 
	private By checkBox = By.cssSelector("[class='mat-checkbox-layout']");
	private By buttonLater = By.xpath("//span[contains(text(), 'LATER')]");
	private By mailBox = By.xpath("//div[contains(text(), ' Zurich ')]");
	
	

	public void getLogin(String email, String languageWant, String passward) {
		
		
		
		ComponentHandler.Sleep(2);
		ComponentHandler.Send(inputEmail,email);
		ComponentHandler.Send(inputPass,passward);
		ComponentHandler.Sleep(3);
		ComponentHandler.Click(buttonLogin);
		ComponentHandler.Sleep(4);
		if(ComponentHandler.IsElementPresent(checkBox)) {
			//ComponentHandler.Click(checkBox);
			ComponentHandler.Sleep(2);
			ComponentHandler.Click(buttonLater);
		}
		ComponentHandler.Print("~~~~~ Login successful - pcx ~~~~~");
		ComponentHandler.Sleep(2);
		
	}
}
