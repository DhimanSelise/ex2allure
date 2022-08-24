package pcx.modules;

import org.openqa.selenium.By;

import core.components.ComponentHandler;

public class EditCase {
	private By btnCases = By.cssSelector("[href=\"/cases\"]");
	private By btnAdditonalOption = By.cssSelector("[id=\"additionalOptions\"]"); 
	private By btnOrderDay = By.xpath("(//mat-icon[@id=\"am-1374\"])[3]");
	private By btnSelectToday = By.cssSelector("[class=\"mat-calendar-body-cell-content mat-calendar-body-today\"]");
//	private By btnSelectDay4 = By.xpath("//div[text()='13']");
	private By btnSelectDay4 = By.cssSelector("[class='mat-calendar-body-cell-content mat-calendar-body-today']");
	private By btnApplyFilter = By.cssSelector("[id='am-1081']");
	
	// get email
	
	private By selectEditMail = By.xpath("(//div[@class='datatable-row-center datatable-row-group ng-star-inserted'])[1]") ; 
	private By SelectedCase = By.xpath("(//datatable-body-row)[1]");
	private By btnEditCase = By.cssSelector("[id=\"am-1005\"]");
	
	private By catagoryList = By.xpath("(//mat-select[@formcontrolname='TestingCategory'])");
	
	private By btnService1Expand  = By.xpath("(//button[@id=\"am-1175\"])[1]/following-sibling::mat-icon");
	private By btnService2Expand  = By.xpath("(//button[@id=\"am-1175\"])[2]/following-sibling::mat-icon");
	
	private By cat1A = By.xpath("(//mat-select[@formcontrolname='TestingCategory'])[1]");
	private By cat1Aok = By.xpath("//span[text()='Material']");
	
	private By cat2A = By.xpath("(//mat-select[@formcontrolname='TestingCategory'])[2]"); 
	private By cat2Aok = By.xpath("//span[text()='Wage']");
	private By cat2B = By.xpath("(//mat-select[@formcontrolname='TestingCategory'])[3]"); 
	private By cat2Bok = By.xpath("//span[text()='complex']");
	
	
	private By getEmail = By.xpath("(//a[@class=\"user-email\"])[2]");
	
	private By btnEditCaseOK = By.xpath("//span[text()='EDIT CASE']");
	
	private By EditCaseSave = By.cssSelector("[id=\"am-1125\"]"); 
	
	String BTNgENERATErEPORT = "//span[text()='GENERATE REPORT']";
	static int rand(int max,int min) {
		return (int)(Math.random()*(max-min+1)+min);
	}

	public void EditCaseNow(String caseID,int[] v) {
		
		ch.Sleep(0.5);
		ComponentHandler.Click(btnCases);
		ch.Sleep(3);
		
		//SmokeFix 1 comments next one added
		//ComponentHandler.Click("#additionalOptions");
		ch.ClickByText("expand_more","mat-icon");
		
		ch.Sleep(0.5);
		ch.Click("#am-1080"); // Clear
		ch.Sleep(3);
		//SmokeFix 1 comments next one added
		//ComponentHandler.Click("#additionalOptions");
		ch.ClickByText("expand_more","mat-icon");
		
		ch.Sleep(0.5);
		ch.Send("#am-1073", caseID);
		ComponentHandler.Sleep(0.5);
		ch.Click("#am-1081"); // apply filter
		ComponentHandler.Sleep(1.5);

		
		int refreshCount = 0 ; 
		
//		while(ComponentHandler.GetSize("(//span[@class='case-claim-style'])[1]")<=2) {
		while(ComponentHandler.GetSize("(//span[@id='caseNumber'])[1]")<1) {
			System.err.println("Need refresh ! Case not found "+refreshCount);
			ComponentHandler.RefreshPage();
			ch.Sleep(2);
			ComponentHandler.Click("#additionalOptions");
			ch.Sleep(0.5);
			ComponentHandler.Click("#am-1080"); // Clear
			ch.Sleep(1.5);
			ComponentHandler.Click("#additionalOptions");
			ch.Sleep(0.5);
			ComponentHandler.Send("#am-1073", caseID);
			ComponentHandler.Sleep(0.5);
			ComponentHandler.Click("#am-1081"); // apply filter
			ComponentHandler.Sleep(1.5);
			refreshCount++; 
			if(refreshCount>2) {
				System.err.println("There is a problem in findings the case ID <"+caseID+"> | Please restart the process. ");
				return;
				
			}
		}
		
		ComponentHandler.Click("(//span[@id='caseNumber'])[1]");
		ComponentHandler.Click(btnEditCaseOK); 

		
		int subItemNo = 1; 
		for(int service=1 ; service < v.length ; service++) {
			ComponentHandler.Print("Editing on Service:"+service);
			
			//ComponentHandler.Click("(//button[@id='am-1175'])["+service+"]/following-sibling::mat-icon"); // Service button expand
			
			//ch.Click("(//mat-icon[@id=\"am-1382\"])["+service+"]");
			ch.Sleep(1);
			ch.Click("#am-1382");
			
			for(int subItem=1 ; subItem <= v[service] ; subItem++) {
				ComponentHandler.Print("Editing on SubItem:"+service+"."+subItem);
				ComponentHandler.Click("(//mat-select[@formcontrolname='TestingCategory'])["+subItemNo+"]"); // Testing Category Click
				ComponentHandler.Sleep(0.5);
				ComponentHandler.Click("(//span[@class='mat-option-text'])["+ComponentHandler.Rand(3)+"]"); // Select any category like Material or Complex or Wage
				ComponentHandler.Sleep(0.5);
				subItemNo++;
			}
		}
		
		ComponentHandler.Sleep(2);
		ComponentHandler.Click(EditCaseSave);
		System.out.println("All Category edit has been successfull.");
		
		//======== Quality Check ===== 
		
		if(ComponentHandler.IsElementPresent("//span[text()='Quality Check']//following-sibling::mat-icon")) {
			ch.Click("//span[text()='Quality Check']//following-sibling::mat-icon"); 
			ch.ClickByText(" Complete");
			ch.Click("(//span[text()=\" Complete\"])[2]");
			ch.Sleep(0.2);
			ch.ClickByText("SAVE");
		}
		
		ComponentHandler.Sleep(5);
		if(ComponentHandler.IsElementPresent(BTNgENERATErEPORT)) {
			//ObjectRepo.driver.navigate().refresh();
			ComponentHandler.Sleep(2);
			ch.Click(BTNgENERATErEPORT);
			ComponentHandler.Sleep(5);
			//ComponentHandler.Click(BTNgENERATErEPORT);
			System.out.println("Report Generation edit has been successfull.");
			while(ComponentHandler.IsElementPresent("(//span[text()='SEND'])[1]")) {
				System.out.println("Send button found");
				break;
			}
			//ch.Sleep(10);
		}
				
		
	}
}
