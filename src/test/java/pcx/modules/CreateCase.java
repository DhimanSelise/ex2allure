package pcx.modules;

import org.openqa.selenium.By;

import core.components.ComponentHandler;
import pcx.commons.Locators;
import pcx.commons.config;


public class CreateCase {

		private By assignUser = By.xpath("//button[contains(@class,'inspector-selector')]");		
		private By addMoreService = By.xpath("//span[text()=' ADD MORE ']");
		
		// Inspector Report
		
		private By rdoPrice = By.xpath("((//div[@id='am-1158'])[1]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]");
		private By rdoPlausibility1 = By.xpath("((//div[@id='am-1158'])[2]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]");
		private By rdoPlausibility2 = By.xpath("((//div[@id='am-1158'])[3]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]");
		private By rdoCompensation = By.xpath("((//div[@id='am-1158'])[4]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]");
		private By rdoLiability = By.xpath("((//div[@id='am-1158'])[5]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]");
		private By rdoRecommendedAction = By.xpath("((//div[@id='am-1158'])[6]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]");
		
		private By commentPrice = By.xpath("(//textarea[@id='am-1161'])[1]");
		private By commentPlausibility1 = By.xpath("(//textarea[@id='am-1161'])[2]");
		private By commentPlausibility2 = By.xpath("(//textarea[@id='am-1161'])[3]");
		private By commentCompensation = By.xpath("(//textarea[@id='am-1161'])[4]");
		private By commentLiability = By.xpath("(//textarea[@id='am-1161'])[5]");
		private By commentRecommendedAction = By.xpath("(//textarea[@id='am-1161'])[6]");
		
		// Right side Setup
//		private By inpAssignedBackoffice = By.xpath("(//input[@id='am-1095'])[1]"); 
		private String inpAssignedBackoffice = "[formcontrolname='AssignedBackOfficeSearchText']"; 
		private By rdoOfficierSelect = By.xpath("//mat-radio-group[@formcontrolname='BackofficeUserId']/div/div/div[2]/mat-radio-button/label/div[1]");
	//	private By inpAssignedInspector = By.xpath("(//input[@id='am-1095'])[2]"); 
		private By inpAssignedInspector = By.cssSelector("[formcontrolname='AssignedInspectorSearchText']") ;  
		private By rdoInspecterSelect = By.xpath("//mat-radio-group[@formcontrolname=\"InspectorUserId\"]/div/div/div[2]/mat-radio-button/label/div[1]");
		
		private By btnSaveAll = By.cssSelector("[id='am-1147']"); // Case Save
		
		
		public void Sleep(double n) {
			ComponentHandler.Sleep(n);
		}
		
		public Boolean isBlank(String Locator) {
			int val = ComponentHandler.GetTextOfInput(Locator).length();
			if(val==0) return true;
			return false;
		}
		public void CheckBlankAndSend(String Locator,String message) {
			if(isBlank(Locator)) {
				ComponentHandler.Send(Locator, message);
			}
		}
		
		public String Trim(String yourString,int maxLength) {
				System.out.println("Before:"+yourString);
				String tmp = yourString.substring(0, Math.min(yourString.length(), maxLength));
				return tmp;
		}
		
		public String TrimByLocator(String Locator,int maxLength) {
			String yourString=ComponentHandler.GetTextOfInput(Locator);
			return Trim(yourString,maxLength);
		}
		public boolean isSaveOn = true;
		public String createCase(int[] v, String backOfficeName,String inspectorName) {
			
			String caseID = "Deleted Item -"+ComponentHandler.Rand(9999999);
//			if(inspectorName=="IPEX Staging" || backOfficeName=="IPEX Staging") {
//				isSaveOn = false;
//			}
			// Dynamic Locators ===================================================
			
			// Service 2 
			int serviceNo = 1;
			int subItemNo = 1; 
			int libilityAssessmentNo = 1; // += 4 for next yes 
			
			
			
			ComponentHandler.Click(Locators.AfterLogin.mailBox);
//			for(int i=0 ; i<= 10 ; i++) {
//				ComponentHandler.Click(nextMail);
//				ComponentHandler.Sleep(0.5);
//			}
			ComponentHandler.Sleep(2);
			ComponentHandler.Click(Locators.AfterLogin.selectedMail);
			ComponentHandler.Sleep(2);
			ComponentHandler.Click(Locators.CreateCase.btnCreateCase);
			ComponentHandler.Sleep(5);
			
			
			
			// After Create case Button
			ComponentHandler.ClearAndSend(Locators.CreateCase.inpInsuranceClaimNumber,caseID);
	//		ComponentHandler.Send(inpInsuranceOfficerName,"Dhiman Sarker");
	//		ComponentHandler.Send(inpInsuranceOfficerEmail,"dhimansarker@selise.ch");
				// ======== Calender handaler 
			
			ComponentHandler.Send(Locators.CreateCase.inpZipCodeOfIssuer,"4500");
			ComponentHandler.Send(Locators.CreateCase.inpName,"Robin Bhaiya");
//			ComponentHandler.Send(inpCompanyName,"Selise");
//			ComponentHandler.Send(inpEmailAddress,"test@selise.ch");
			
//			String inpConstructionCompanyPhoneNo = "(//input[@placeholder='Phone'])[1]";
//			ch.Sleep(1);
//			ComponentHandler.ClearAndSend(inpConstructionCompanyPhoneNo, "+8801684216333");
//			ch.Sleep(1);
//			ComponentHandler.Clear(inpPhoneNumber);
//			ComponentHandler.Click(btnCountryCodes);
//			ComponentHandler.Click(btnSelectBD);
//			ComponentHandler.Send(inpPhoneNumber,"1684216333");
			
			
			ch.Send("//ng-select[@id=\"am-1375\"]/div/div/div/input", config.CaseInfo.caseType); // Case Type
			ch.Sleep(0.5);
			ch.Click("(//div[@role=\"option\"])[1]"); // Select first option
			
			ComponentHandler.Print("Deleting Current Services...");
			int nums = 1 ; 
			while(ComponentHandler.IsElementPresent(Locators.CreateCase.deleteServices)) {

				ch.Click(Locators.CreateCase.deleteServices);
				ComponentHandler.Sleep(2);
				ch.ClickByText("delete_forever", "mat-icon");
				ch.Sleep(0.5);
				ch.Click("[aria-label='Save']");
				ch.Sleep(0.5);
				System.out.println(nums+" item(s) has been deleted!");
				nums++;
			}
			ComponentHandler.Print("All service has been deleted===");
			
			int totalSubItem = -v[0];
			for(int val:v) {
				totalSubItem += val;
			}
			 //==================================================================================================
			for(int service= 1 ; service <= v[0] ; service++ ) {
				serviceNo = service;
				ComponentHandler.Print("Creating on Service:"+serviceNo);
				ComponentHandler.Click(addMoreService);
				//ComponentHandler.Click(item1expand);
				// Service 1 
				ComponentHandler.Sleep(0.5);
				ComponentHandler.Send("(//input[@formcontrolname='ConstructionCompanyName'])["+serviceNo+"]","Service "+service);
				ComponentHandler.Sleep(0.5);
				
				
	// SmokeFix	1	
	// ComponentHandler.Send("(//input[@formcontrolname='ServiceItemTitle'])["+serviceNo+"]","Item Name - "+serviceNo);
				
				
				//ComponentHandler.Click("(//mat-select[@id='am-1179'])["+serviceNo+"]");
				//ComponentHandler.Click("(//span[text()='2.5 '])");
				//ComponentHandler.Click("(//span[@class='mat-option-text'])["+ComponentHandler.Rand(1,3)+"]"); // This will select randrom VAT from 3 option 7.7 or 2.5 or 0
				ComponentHandler.Send("(//input[@formcontrolname='UpdatedOfferNumber'])["+serviceNo+"]",ComponentHandler.Rand(5,420,"Offer Number"));  // Offer Number
				ComponentHandler.Click("(//mat-radio-group[@id='am-1190']/mat-radio-button/label/div)["+libilityAssessmentNo+"]");
				ComponentHandler.Sleep(0.5);
				ComponentHandler.Send("(//input[@id='am-1191'])["+serviceNo+"]", ComponentHandler.Rand(18,75,"String"));
				int numberOfSubItem = v[service];
				libilityAssessmentNo+=4;
				
				for(int subItem = 1 ; subItem <= numberOfSubItem ; subItem++) {
					if(subItemNo>totalSubItem) {
						System.err.println("Breaked By Total condition");
						break;
					}
					ComponentHandler.Sleep(0.5);
					ComponentHandler.Print("Creating on SubItem:"+serviceNo+"."+subItem);
					ComponentHandler.Sleep(0.5);
					ComponentHandler.Send("(//input[@id='am-1200'])["+subItemNo+"]","Title - "+service+"."+subItem);
					ComponentHandler.Sleep(0.5);
					ComponentHandler.Click("(//mat-select[@id='am-1193'])["+subItemNo+"]");
					Sleep(1);
					ComponentHandler.Click("//mat-option[text()='Liter']");
					Sleep(1);
					ComponentHandler.Send("(//input[@id='am-1194'])["+subItemNo+"]",ComponentHandler.Rand(5,10,"String"));
					Sleep(0.5);
					ComponentHandler.Send("(//input[@formcontrolname='UnitPrice'])["+subItemNo+"]",ComponentHandler.Rand(1,100,"String"));
					if(subItem < numberOfSubItem) {
						//ComponentHandler.Click("(//span[text()=' ADD SUB ITEM '])["+serviceNo+"]");
						ComponentHandler.Sleep(0.5);
						ComponentHandler.Click("(//button[@id='am-1203'])["+serviceNo+"]"); // Add sub item
					}
					
					subItemNo++;
				}
			}
			
			System.out.println("Inspector Report Started..."); // SmokeFix 6 by last
			ComponentHandler.Click(rdoPrice); //ComponentHandler.Send(commentPrice, config.InspectorReport.priceText);
			ComponentHandler.Click(rdoPlausibility1); //ComponentHandler.Send(commentPlausibility1, config.InspectorReport.plausibility1Text);
			ComponentHandler.Click(rdoPlausibility2); // ComponentHandler.Send(commentPlausibility2, config.InspectorReport.plausibility2Text);
			ComponentHandler.Click(rdoLiability); //ComponentHandler.Send(commentLiability, config.InspectorReport.LibalityText);
			ComponentHandler.Click(rdoCompensation);// ComponentHandler.Send(commentCompensation, config.InspectorReport.compensationText);
			ComponentHandler.Click(rdoRecommendedAction); //ComponentHandler.Send(commentRecommendedAction, config.InspectorReport.recommendedActionText);
			System.out.println("End");
			
			// Right side Setup ********************* Starts
			
			ComponentHandler.ClickNthElement(assignUser, 0);
			ComponentHandler.Sleep(3);
			ComponentHandler.Send(inpAssignedBackoffice, backOfficeName);
			ComponentHandler.Sleep(3);
			ch.ClickByText(backOfficeName);
			//ComponentHandler.Click(rdoOfficierSelect);
			
			ComponentHandler.ClickNthElement(assignUser, 1);
			ComponentHandler.Sleep(3);
			ch.Sleep(1);
			ComponentHandler.Send(inpAssignedInspector, inspectorName);
			ComponentHandler.Sleep(3); 

			ch.ClickByText(inspectorName);
			//ch.Sleep(100);
			
			if(isSaveOn)ComponentHandler.Click(btnSaveAll);
			else {
				System.out.println("Stage : Save button is "+isSaveOn);
			}
			
			ComponentHandler.Print("Case Create Successful=========");
			return caseID;
			
		}
		
		/*
		 * ###########################################################################
		 * 																				
		 * 						PARA SHIFT Create Case 
		 * 
		 * ###########################################################################
		 */		
		public ArrayAndString ParashiftCreateCase(int mailPageNo,int mailNo, String backOfficeName,String inspectorName) {
			
		// == pre process =========	
			
			
			
		// ============= Variable for parashift type mail ====================
			
			By mailBox = By.xpath("//div[contains(text(), ' Zurich ')]");
			By selectedMail = By.xpath("(//mat-card[@id='am-1263'])["+mailNo+"]");
			String ServiceExpandButton = "[id='am-1382']";	
			
			
		// Dynamic Locators ===================================================
			
//			if(inspectorName=="IPEX Staging" || backOfficeName=="IPEX Staging") {
//				isSaveOn = false;
//				System.out.println("Running on Stage");
//			}
			
			// Service 2 
			int serviceNo = 1;
			int subItemNo = 1; 
			int libilityAssessmentNo = 1; // += 4 for next yes 
			
			
			Sleep(0.5);
			ComponentHandler.Click(mailBox);
			ComponentHandler.Sleep(2);
			for(int i=1 ; i< mailPageNo ; i++) {
				
				ComponentHandler.Click(Locators.AfterLogin.nextMail);
				ComponentHandler.Sleep(0.5);
				
			}
			ComponentHandler.Sleep(2);
			ComponentHandler.Click(selectedMail);
			ComponentHandler.Sleep(2);
			ComponentHandler.Click(Locators.CreateCase.btnCreateCase);
			
			ComponentHandler.Sleep(5);
			String uniqueCaseID = ComponentHandler.GetTextOfInput(Locators.CreateCase.inpInsuranceClaimNumber);
			uniqueCaseID = uniqueCaseID + ComponentHandler.Rand(999999999); 
			ComponentHandler.Clear(Locators.CreateCase.inpInsuranceClaimNumber);
			ComponentHandler.ClearAndSend(Locators.CreateCase.inpInsuranceClaimNumber, uniqueCaseID);Sleep(0.2);
			
			
			ComponentHandler.Send(Locators.CreateCase.inpZipCodeOfIssuer,config.CaseInfo.issuerZip);Sleep(0.2);
			ComponentHandler.Send(Locators.CreateCase.inpName,config.CaseInfo.name);Sleep(0.2);
			ComponentHandler.Send(Locators.CreateCase.inpCompanyName,config.CaseInfo.companyName);Sleep(0.2);
			ComponentHandler.Send(Locators.CreateCase.inpEmailAddress,config.CaseInfo.emailAddress);Sleep(0.2);
			
			
			ComponentHandler.Click(Locators.CreateCase.btnCountryCodes);Sleep(0.2);
			ComponentHandler.Click(Locators.CreateCase.btnSelectBD);Sleep(0.2);
			
			ch.Sleep(1);
			ComponentHandler.ClearAndSend(Locators.CreateCase.inpPhoneNumber, config.CaseInfo.phoneNumber);
			ch.Sleep(1);
			
			ch.Send("//ng-select[@id=\"am-1375\"]/div/div/div/input", config.CaseInfo.caseType); // Case Type
			ch.Sleep(0.5);
			ch.Click("(//div[@role=\"option\"])[1]"); // Select first option
			
			int totalNumberOfService = ComponentHandler.GetSize(ServiceExpandButton);
			
			int a[]=new int[totalNumberOfService+1];
			
			a[0]=totalNumberOfService;
			System.out.println(totalNumberOfService);
			
			int prevSubItem =0; 
			
			System.err.println("totalNumberOfService:"+totalNumberOfService);
			
			for(int service= 1 ; service <= totalNumberOfService ; service++ ) {
				
				Sleep(0.2);
				ComponentHandler.Click("(//mat-icon[@id='am-1382'])[1]");
				
				// ======= Variable for Service ========
				
				serviceNo = service;
				
				ComponentHandler.Print("Creating on Service:"+serviceNo);
				String inpConstructionCompanyName = "(//input[@formcontrolname='ConstructionCompanyName'])["+serviceNo+"]";
				String inpServiceItemTitle = "(//input[@formcontrolname='ServiceItemTitle'])["+serviceNo+"]";
				String inpConstructionCompanyEmail = "(//input[@formcontrolname='ConstructionCompanyEmail'])["+serviceNo+"]";
				String inpAge = "(//input[@id='am-1191'])["+serviceNo+"]";
				
				// Service 1 
				String ConstructionCompanyNameValue = TrimByLocator(inpConstructionCompanyName, 49);
				ComponentHandler.Sleep(0.5);
				if(ConstructionCompanyNameValue.length()==0) {
					ComponentHandler.Send(inpConstructionCompanyName,config.CaseInfo.constructionCompanyName);
				}
				else {
					Sleep(0.5);
					ComponentHandler.ClearAndSend(inpConstructionCompanyName,ConstructionCompanyNameValue);
				}
				
				ComponentHandler.Sleep(0.5);

				CheckBlankAndSend("(//input[@formcontrolname='ConstructionCompanyContact'])["+serviceNo+"]", config.CaseInfo.constructionCompanyConactPerson);
				ComponentHandler.Sleep(0.5);
				
				if(isBlank(inpConstructionCompanyEmail)) {
					ComponentHandler.Send("(//input[@formcontrolname='ConstructionCompanyEmail'])["+serviceNo+"]",config.CaseInfo.constructionCompanyConactEmail);
				}
//				ComponentHandler.ClearAndSend(ComponentHandler.GetLocator("(//input[@placeholder=\"Construction Company Phone\"])[1]"), config.CaseInfo.constructionCompanyConactPhone);
				ComponentHandler.ClearAndSend(ComponentHandler.GetLocator("(//international-phone-number[@id=\"am-1138\"]/div/input)["+serviceNo+"]"), config.CaseInfo.constructionCompanyConactPhone);

				ComponentHandler.Sleep(0.5);
		//SmokeFix 3		
//				if(ComponentHandler.GetTextOfInput(inpServiceItemTitle).length()==0) {
//					ComponentHandler.Send(inpServiceItemTitle,"Item Name - "+serviceNo);
//				}
				
				
				ComponentHandler.Click("(//mat-radio-group[@id='am-1190']/mat-radio-button/label/div)["+libilityAssessmentNo+"]");
				ComponentHandler.Sleep(0.5);
				int UserFullLife = ComponentHandler.Rand(11,20);
				Sleep(0.5);
				ComponentHandler.ClearAndSend(inpAge,""+(UserFullLife-10));
				
				int totalSubItem = ComponentHandler.GetSize("[id='am-1200']");
				int numberOfSubItem = totalSubItem-prevSubItem;
				a[service] = numberOfSubItem;
				ComponentHandler.Sleep(3);
				prevSubItem = numberOfSubItem;

				libilityAssessmentNo+=4; 
				for(int subItem = 1 ; subItem <= numberOfSubItem ; subItem++) { // nested loop 
					if(subItemNo>totalSubItem) {
						System.err.println("Breaked By Total condition");
						break;
					}
					
					ComponentHandler.Print("Creating on SubItem:"+serviceNo+"."+subItem);
					//-------- Variable for SubItem ------------------
					String inpSubItemTitle = "(//input[@id='am-1200'])["+subItemNo+"]";
					//------------------------------------------------
					Sleep(0.2);
					CheckBlankAndSend(inpSubItemTitle, "Title - "+service+"."+subItem);
					
					
					CheckBlankAndSend("(//input[@formcontrolname='Quantity'])["+subItem+"]", ComponentHandler.Rand(1, 30,""));
					CheckBlankAndSend("(//input[@formcontrolname='UnitPrice'])["+subItem+"]", ComponentHandler.Rand(1, 30,""));
					
					
					subItemNo++;
				}
			}
		
			System.out.println("Inspector Report Started..."); // SmokeFix 6 by last
			ComponentHandler.Click(rdoPrice); //ComponentHandler.Send(commentPrice, config.InspectorReport.priceText);
			ComponentHandler.Click(rdoPlausibility1); //ComponentHandler.Send(commentPlausibility1, config.InspectorReport.plausibility1Text);
			ComponentHandler.Click(rdoPlausibility2); // ComponentHandler.Send(commentPlausibility2, config.InspectorReport.plausibility2Text);
			ComponentHandler.Click(rdoLiability); //ComponentHandler.Send(commentLiability, config.InspectorReport.LibalityText);
			ComponentHandler.Click(rdoCompensation);// ComponentHandler.Send(commentCompensation, config.InspectorReport.compensationText);
			ComponentHandler.Click(rdoRecommendedAction); //ComponentHandler.Send(commentRecommendedAction, config.InspectorReport.recommendedActionText);
			System.out.println("End");
			
			// Right side Setup
			
			ch.Sleep(2);

			
			ComponentHandler.Print("Assigning Inspector... [Para]");
			
			ComponentHandler.ClickNthElement(assignUser, 0);
			ComponentHandler.Sleep(3);
			ComponentHandler.Send(inpAssignedBackoffice, backOfficeName);
			ComponentHandler.Sleep(3);
			ch.ClickByText(backOfficeName);
			//ComponentHandler.Click(rdoOfficierSelect);
			
			ComponentHandler.ClickNthElement(assignUser, 1);
			ComponentHandler.Sleep(3);
			ch.Sleep(1);
			ComponentHandler.Send(inpAssignedInspector, inspectorName);
			ComponentHandler.Sleep(3); 

			ch.ClickByText(inspectorName);
			//ch.Sleep(100);
			
			if(isSaveOn)ComponentHandler.Click(btnSaveAll);
			else {
				System.out.println("Stage : Save button is "+isSaveOn);
			}
			
			ComponentHandler.Print("Case Create Successful=========");
			
			// =========================== Findings of created Mail ================
			
			ComponentHandler.Sleep(2);
		
			
			System.err.print("Returned Array: ");
			for(int val:a) {
				System.err.print(val+",");
			}
			
			ArrayAndString myData = new ArrayAndString();
			myData.itemAndSubItemList = a; 
			myData.caseTitle = uniqueCaseID;
			
			return myData;
		}
	
}
