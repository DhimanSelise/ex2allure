package pcx.modules;

import org.openqa.selenium.By;

import core.components.ComponentHandler;
import core.components.ObjectRepository;

public class getInspectorMail {
		//private By selectEditMail = By.xpath("(//div[@class='datatable-row-center datatable-row-group ng-star-inserted'])[1]") ; 
		
	
		private By getEmail = By.xpath("(//a[@class=\"user-email\"])[1]");
		
		private By btnUser = By.cssSelector("[href=\"/users\"]");
		private By searchBox =  By.cssSelector("[id=\"am-1308\"]");
		private By mailBox = By.xpath("(//div[text()='Ecap Class'])[1]");
		
		private By dataInpspectorMail = By.xpath("(//div[text()='Email:']/following-sibling::div)");
		
	
		public String getInspectorsMail(String inspectorName) {
			
			System.out.println("--> Finding Inspector mail <--");
			
			// Select Edited 
			//ComponentHandler.Click(selectEditMail);
			
			ComponentHandler.Sleep(3);
			ComponentHandler.Click(btnUser);
			ComponentHandler.Sleep(1);
			ComponentHandler.Send(searchBox, inspectorName);
			ComponentHandler.Sleep(3);
			//ComponentHandler.Click(mailBox);
			String mailData = ObjectRepository.driver.findElement(getEmail).getText();
			System.out.println("Mail found: "+mailData);
			return mailData;
		}
}
