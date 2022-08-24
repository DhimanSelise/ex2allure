package pcx.modules;

import org.testng.Assert;

import core.components.ComponentHandler;
import pcx.commons.config;

public class AdditionalModule {
	public void QualityCheckAssign(String userEmail,String value) {
		System.out.println("QualityCheckAssign module started");
		ch.Sleep(1);
		ch.ClickByText("Users");
		ch.Sleep(1);
		ch.Send("[id='am-1308']", userEmail); // Serach user by email
		ch.Sleep(2);
		int userCount = ComponentHandler.GetSize("//datatable-row-wrapper") ; 
		System.out.println("User Count = "+userCount);
		
		if(userCount==10) {
			System.err.println("Trying again for ensure...");
			ComponentHandler.ClearAndSend("[id='am-1308']", userEmail); // Serach user by email
			ch.Sleep(3);
			userCount = ComponentHandler.GetSize("//datatable-row-wrapper") ; 
			System.out.println("User Count = "+userCount);
		}
		
		Assert.assertEquals(userCount, 1);
		
		String currentVal = ComponentHandler.GetText("(//div[@class='datatable-body-cell-label'])[5]/div/div/span")  ; 
		if(userCount==1) {
			ch.Click("(//div[@class=\"datatable-body-cell-label\"])[5]/div");
			ComponentHandler.ClearAndSend("[formcontrolname='CaseNumber']", ""+value);
			ch.ClickByText(" Save ");
			ch.Sleep(5);
			if(!(currentVal.equals("Assign")))ch.Click("(//div[@class=\"datatable-body-cell-label\"])[5]/div");
			ch.Click("(//div[@class=\"datatable-body-cell-label\"])[5]/div");	
			String nums = ComponentHandler.GetTextOfInput("[formcontrolname='CaseNumber']") ; 
			System.out.println("nums = "+nums);
			Assert.assertEquals(nums, ""+value);
		}
		else {
			System.out.println("User not found");
			Assert.assertEquals(userCount+" user(s)", "1 user");
		}
	}
	
	public void StatusUpdate(String email) {
		ch.ClickByText("Users");
		ch.Sleep(2);
		ch.Send("#am-1308", email);
		ch.Sleep(3);
		int usersListSize  = ComponentHandler.GetSize("//datatable-body-row") ; 
		if(usersListSize==1) {
			//ch.Click("//datatable-body-row/div/datatable-body-cell[7]/div/div/mat-slide-toggle/label/div/input"); // Toggle button
			String currentStatus = ComponentHandler.GetAttribute("datatable-body-row div datatable-body-cell div div mat-slide-toggle label div input", "aria-checked") ; 
			
			ch.Click("datatable-body-row div datatable-body-cell div div mat-slide-toggle label div input"); // Toggle button
			ch.Sleep(3);
			String clickStatus = ComponentHandler.GetAttribute("datatable-body-row div datatable-body-cell div div mat-slide-toggle label div input", "aria-checked") ; 
			
			
			System.out.println(currentStatus+" -> "+clickStatus);
			if(currentStatus.equals(clickStatus)) {	
				System.out.println("Not Changed");
			}
			else {
				System.out.println("Chnaged");
				assert true;
			}
			
		}
		else {
			System.out.println("User not found by the mail : "+email);
			assert false;
		}
		
	}
	
	public void ClickOnAValidCase() {
		ch.ClickByText("Cases");
		ch.Sleep(1);
		while(!(ComponentHandler.IsElementPresent("(//mat-icon[@mattooltipclass='tooltip' and text()='check_circle '])[1]"))) {
			ch.Click("[aria-label='go to next page']");
			ch.Sleep(2);
		}
		ch.Click("(//mat-icon[@mattooltipclass='tooltip' and text()='check_circle '])[1]"); 
		ch.Sleep(2);
	}
	
	public void GenerateReportAndSendReport(){
		System.out.println("Generate Report And Send Report");
		ClickOnAValidCase();
		ch.ClickByText("GENERATE REPORT");
		ch.Sleep(1);
		ch.ClickByText("SEND");
		
	}
	
	public void CheckClickableCall() {
		System.out.println("******* Checking Clickable Call **********");
		ClickOnAValidCase();
		ch.Click("#am-1003");
		ch.Sleep(1);
		ComponentHandler.ClearAndSend("#am-1168 div input", "+8801684216333");
		ch.Sleep(0.5);
		ch.ClickByText("call", "mat-icon");
		ch.Sleep(10);
	}
	
	public void CheckClickableMail() {
		System.out.println("******* Checking Clickable eMail **********");
		ClickOnAValidCase();
		ch.Click("#am-1003");
		ch.Sleep(1);
		ComponentHandler.Clear("#am-1167");
		ch.Send("#am-1167", "chessdhiman@gmail.com");
		ch.Sleep(5);
//		ch.Send("#am-1166", "No Name");
//		ch.Sleep(5);
		ch.Click("[href='mailto:chessdhiman@gmail.com']");
		//ch.ClickByText("email", "mat-icon");
		ch.Sleep(10);
	}
		
}



