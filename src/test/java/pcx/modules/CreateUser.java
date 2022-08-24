package pcx.modules;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;

import core.components.ComponentHandler;
import core.components.ObjectRepository;
import pcx.commons.Locators;
import pcx.commons.config;

public class CreateUser {
	static int rand(int max,int min) {
		return (int)(Math.random()*(max-min+1)+min);
	}
	
//	public boolean makeNewUser1(String firstName,String secondName,String Email) {
	
	public boolean makeNewUser1(String SP,String EP) {
		
		ComponentHandler.Click(Locators.createUser.btnUsers);
		ComponentHandler.Click(Locators.createUser.btnCreatUser);
		
		switch (SP) {
		case "":
			
			ch.Sleep(2);
//			ObjectRepository.driver.findElement(By.cssSelector("[type='file']")).sendKeys("E:\\Selise\\lim\\Img\\userImg.jpg");
//			ch.Sleep(2);  // This will upload file from local pc
			ComponentHandler.Click(Locators.createUser.btnSalutation);
			ComponentHandler.Click(Locators.createUser.btnMr);
			
			if(EP.equals(Locators.createUser.inpFirstName)) return false;
			case Locators.createUser.inpFirstName:
				
			ComponentHandler.Send(Locators.createUser.inpFirstName, config.NewUserInfo.firstName);
			
			if(EP.equals(Locators.createUser.inpLastName)) return false;
			case Locators.createUser.inpLastName:
			ComponentHandler.Send(Locators.createUser.inpLastName, config.NewUserInfo.lastName);

			ComponentHandler.Click(Locators.createUser.rdoGender);
			ComponentHandler.Click(Locators.createUser.optionRole);
			ComponentHandler.Click(Locators.createUser.RoleSelect);
			
			ComponentHandler.Click(Locators.createUser.optionPhone);
			ComponentHandler.Click(Locators.createUser.selectBD);
			ComponentHandler.Send(Locators.createUser.inputPhone, config.NewUserInfo.phoneNo);
			
			
			if(EP.equals(Locators.createUser.inpEmail)) return false;
			case Locators.createUser.inpEmail:
			ComponentHandler.Send(Locators.createUser.inpEmail, config.NewUserInfo.email);
			ComponentHandler.Sleep(2);
			
			
			if(EP.equals(Locators.createUser.SaveUser)) return false;
			case Locators.createUser.SaveUser:
				
			
			
			if(ComponentHandler.IsElementPresent(Locators.createUser.isUserExits)) {
				System.err.println("The user <"+config.NewUserInfo.firstName+" "+config.NewUserInfo.lastName+"> with email <"+config.NewUserInfo.email+ "> is already EXISTS");
				ComponentHandler.Click(Locators.createUser.btnCancel);
				System.out.println("Failed to create new user");
				ch.Sleep(3);
				return false;
			}
			else {

				ComponentHandler.Click(Locators.createUser.SaveUser);
				System.out.println("Please wait for sending email");
				System.out.println("Create user works");
				ch.Sleep(3);
				return true;
			}
		default:
			return false;
			
		}

		
		
	}
	
	//===================== Function for verify Mail ==========================
	

			
			
	
	public void verifyMail(String email,String NewPass) {
		
		
		System.out.println("Verified Mail: "+email);
		ComponentHandler.NavigateToURL("https://yopmail.com/en/");
		ch.Sleep(5);
		ComponentHandler.Send(Locators.VerifyEmail.emailInput, email);
		ComponentHandler.Click(Locators.VerifyEmail.btnArrow);
		ComponentHandler.Sleep(5);
		
		ObjectRepository.driver.switchTo().frame("ifmail");
		ComponentHandler.Click("//a[text()='ACtivate account']");
		
		ObjectRepository.driver.switchTo().defaultContent();
		
		String parentHandle = ObjectRepository.driver.getWindowHandle();
		
		Set<String> handles = ObjectRepository.driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				ObjectRepository.driver.switchTo().window(handle);
				ComponentHandler.Send("[name='password']",NewPass);
				ch.Sleep(1);
				ComponentHandler.Send("[name='confirmPassword']",NewPass);
				ComponentHandler.Sleep(1);
				ComponentHandler.Click("[aria-label='LOGIN']");
				ch.Sleep(1);
				ComponentHandler.Click("[aria-label='LOGIN']");
				
			}
		}
		System.out.println("Mail Verified");
		
	}
	
	//--------------------------------  Ends > Verify Mail -------------------------------
	
	
	//================================ Find User ========================================
	
	public void findUser(String userEmail) {
		System.out.println("Find User module started");
		ch.Sleep(1);
		ch.ClickByText("Users");
		ch.Sleep(1);
		ComponentHandler.Clear("#am-1308");ch.Sleep(1);
		ch.Send("[id='am-1308']", userEmail); // Serach user by email
		ch.Sleep(2);
		int userCount = ComponentHandler.GetSize("//datatable-row-wrapper") ; 
		Assert.assertEquals(userCount, 1);
		if(userCount==1) {
			ch.Click("(//datatable-body-cell)[1]");
			//ch.Click("(//datatable-row-wrapper/datatable-body-row)[1]");
			ch.Sleep(1);
			String emailData = ComponentHandler.GetText("//div[text()='Email']/following-sibling::div") ; 
			emailData.trim();
			
			if(emailData.toLowerCase().equals(userEmail.toLowerCase())) {
				System.out.println("--------------Match Found-------------");
				System.out.println(emailData.toLowerCase() +" and " + userEmail.toLowerCase());
				System.out.println("---------------------------------------");
			}
			Assert.assertEquals(emailData.toLowerCase(), userEmail.toLowerCase());
		}
		
	}
	
	//============================== Delete existing User ==========================
	
	public void deleteUser(String userEmail) {
		findUser(userEmail);
		ch.ClickByText("delete_forever","mat-icon");
		ch.Sleep(1);
	//	ch.Click("[class='yes-button']");
	//	ch.ClickByText(" Confirm ");
		ch.Click("[aria-label=\"Save\"]");
		ch.Sleep(2);
		deleteUserValidation(userEmail);
	}
	
	public void deleteUserValidation(String email) {
		System.out.println("Delete user validation on going...");
		ch.Sleep(1);
		ch.ClickByText("Users");
		ch.Sleep(1);
		ch.Send("[id='am-1308']", email); // Serach user by email
		ch.Sleep(2);
//		int userCount = ComponentHandler.GetSize("//datatable-row-wrapper") ; 
//		Assert.assertEquals(userCount, 0);
		String userCountData = ComponentHandler.GetText("[class='page-count ng-star-inserted']");
		userCountData.trim();
		Assert.assertEquals(userCountData.toLowerCase(), "0 total");
				
	}
	public void updateUser(String email) {
		System.out.println("Working on update user");
		ch.Sleep(1);
		findUser(email);
		ch.ClickByText("edit", "mat-icon");
		
		
		
		ComponentHandler.ClearAndSend("[id='am-1343']", config.UpdatedUserInfo.updatedLastName);
		ComponentHandler.ClearAndSend("[id='am-1342']", config.UpdatedUserInfo.updatedFirstName);
		
		ch.ClickByText("UPDATE");
		ch.Sleep(3);
		String updatedName = ComponentHandler.GetText("[style='color: rgba(0, 10, 27, 0.8);']");
		updatedName.trim();
		updatedName.toLowerCase();
		String splitData[] = updatedName.split("\\s", 2);
		String myData = config.UpdatedUserInfo.updatedFirstName+" "+config.UpdatedUserInfo.updatedLastName ;
		myData.toLowerCase();

		if(splitData[1].equals(myData)) {
			System.out.println("User Updated");
			Assert.assertEquals(true, true);
		}
		else {
			System.out.println("User Not Updated");
			Assert.assertEquals(false, true);
			//Assert.ass
			
		}
		ch.Sleep(2);
	}
	
}
