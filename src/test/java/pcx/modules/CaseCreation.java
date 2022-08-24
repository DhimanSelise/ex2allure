package pcx.modules;
import org.openqa.selenium.By;

import core.components.ComponentHandler;
import pcx.commons.Locators;
import pcx.commons.config;
public class CaseCreation {
	private By createCase = By.xpath("(//button[@id='am-1258'])[2]");
	private By addMoreService = By.xpath("//span[text()=' ADD MORE ']");
	String ServiceExpandButton = "[id='am-1382']";
	int totalNumberOfServiceForCreatedCase = 0;

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
	private By liabilityEvaluation = By.cssSelector("[formcontrolname='LiabilityValuation']");
	private By caseComponentName = By.cssSelector("[formcontrolname='CaseComponentName']");
	private By usefulLife = By.cssSelector("[formcontrolname='UseFullLife']");
	private By usefulLifeToggle = By.id("userfulLifeToggle");
	private By newValue = By.cssSelector("[formcontrolname='NewFairValue']");
	private By newValueToggle = By.id("newValueToggle");
	private By age = By.cssSelector("[formcontrolname='UpdatedAge']");
	private By liabilityAdditionalCommentForTwenty = By.id("liabilityTwentyComment");
	private By discardBtn = By.id("am-1145");
	private By expandServiceList = By.id("expandServiceList");
	
	

// Right side Setup
	private By assignUser = By.xpath("//button[contains(@class,'inspector-selector')]");
//	private By inpAssignedBackoffice = By.xpath("(//input[@id='am-1095'])[1]"); 
	private String inpAssignedBackoffice = "[formcontrolname='AssignedBackOfficeSearchText']"; 
	private By rdoOfficierSelect = By.xpath("//mat-radio-group[@formcontrolname='BackofficeUserId']/div/div/div[2]/mat-radio-button/label/div[1]");
//	private By inpAssignedInspector = By.xpath("(//input[@id='am-1095'])[2]"); 
	private By inpAssignedInspector = By.cssSelector("[formcontrolname='AssignedInspectorSearchText']") ;  
	private By rdoInspecterSelect = By.xpath("//mat-radio-group[@formcontrolname=\"InspectorUserId\"]/div/div/div[2]/mat-radio-button/label/div[1]");
	private By btnSaveAll = By.cssSelector("[id='am-1147']"); // Case Save
	
	public void clickCreateCase() {
		ComponentHandler.Click(createCase);
		System.out.println("Clicked create case button");
	}
	
	public void liabilityValidation() {
		SelectMail selectMail = new SelectMail();
		selectMail.selectMail("Zurich", 0);
		this.clickCreateCase();
		ComponentHandler.Sleep(10);
		ComponentHandler.WaitForElementClickablity(discardBtn, 20); 
		

//		ComponentHandler.WaitUntillVisible(discardBtn);
		ComponentHandler.Sleep(3);
		String caseId = this.fillMandatoryFields();
		String finalLiabilityValue = this.addServiceAndValidateLiability();
		this.viewCaseDetailsAndValidateFinalLiabilityValue(caseId, finalLiabilityValue, this.totalNumberOfServiceForCreatedCase);
		ComponentHandler.Sleep(10);
	}
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
	public String fillMandatoryFields() {
		String backOfficeName = "IPEX Staging";
		String inspectorName = "IPEX Staging";
		// Service 2 
		int serviceNo = 1;
		int subItemNo = 1; 
		int libilityAssessmentNo = 1; // += 4 for next yes 
		String uniqueCaseID = ComponentHandler.GetTextOfInput(Locators.CreateCase.inpInsuranceClaimNumber);
		uniqueCaseID = "LiabilitValidationTest" + ComponentHandler.Rand(999999999); 
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
		this.totalNumberOfServiceForCreatedCase = totalNumberOfService;
		int prevSubItem =0; 
		
		System.err.println("totalNumberOfService:"+totalNumberOfService);
		
		for(int service= 1 ; service <= totalNumberOfService ; service++ ) {
			
			Sleep(0.2);
			ComponentHandler.Click("(//mat-icon[@id='am-1382'])["+service+"]");
			
			// ======= Variable for Service ========
			
			serviceNo = service;
			
			ComponentHandler.Print("Creating on Service:"+serviceNo);
			String inpConstructionCompanyName = "(//input[@formcontrolname='ConstructionCompanyName'])[1]";
			String inpServiceItemTitle = "(//input[@formcontrolname='ServiceItemTitle'])[1]";
			String inpConstructionCompanyEmail = "(//input[@formcontrolname='ConstructionCompanyEmail'])[1]";
			
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

			CheckBlankAndSend("(//input[@formcontrolname='ConstructionCompanyContact'])[1]", config.CaseInfo.constructionCompanyConactPerson);
			ComponentHandler.Sleep(0.5);
			
			if(isBlank(inpConstructionCompanyEmail)) {
				ComponentHandler.Send("(//input[@formcontrolname='ConstructionCompanyEmail'])[1]",config.CaseInfo.constructionCompanyConactEmail);
			}
//			ComponentHandler.ClearAndSend(ComponentHandler.GetLocator("(//input[@placeholder=\"Construction Company Phone\"])[1]"), config.CaseInfo.constructionCompanyConactPhone);
			ComponentHandler.ClearAndSend(ComponentHandler.GetLocator("(//international-phone-number[@id=\"am-1138\"]/div/input)[1]"), config.CaseInfo.constructionCompanyConactPhone);

			ComponentHandler.Sleep(0.5);
			
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
			ComponentHandler.Click("(//mat-icon[@id='am-1381'])[1]");
		}
	
		System.out.println("Inspector Report Started..."); // SmokeFix 6 by last
		ComponentHandler.Click(rdoPrice); //ComponentHandler.Send(commentPrice, config.InspectorReport.priceText);
		ComponentHandler.Click(rdoPlausibility1); //ComponentHandler.Send(commentPlausibility1, config.InspectorReport.plausibility1Text);
		ComponentHandler.Click(rdoPlausibility2); // ComponentHandler.Send(commentPlausibility2, config.InspectorReport.plausibility2Text);
		//ComponentHandler.Click(rdoLiability); //ComponentHandler.Send(commentLiability, config.InspectorReport.LibalityText);
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
		ComponentHandler.Sleep(2);
		return uniqueCaseID;
	}
	
	public String addServiceAndValidateLiability() {
		// Liability Evaluation = ((usefulLife - age) / usefulLife)* totalRepairDamageCost
		// N.B: if newValue is edited manually, then totalRepairDamageCost (sum of sub items that are not enabled for damage related cost) 
		// is replaced by newValue in LE calculation, so LE = ((UL - Age) / UL) * NewValue
		// N.B: usefulLife = Component, when component is set from the select option
		
		ComponentHandler.Print("Working on new service for liability validation");
		String inpConstructionCompanyName = "(//input[@formcontrolname='ConstructionCompanyName'])[1]";
		String inpServiceItemTitle = "(//input[@formcontrolname='ServiceItemTitle'])[1]";
		String inpConstructionCompanyEmail = "(//input[@formcontrolname='ConstructionCompanyEmail'])[1]";
		By addSubitem = By.cssSelector("[class^='add-sub']");
		By subitemTitle1 = By.xpath("(//input[@formcontrolname='ServiceSubItemTitle'])[1]");
		By subitemTitle2 = By.xpath("(//input[@formcontrolname='ServiceSubItemTitle'])[2]");
		
		By quantity1 = By.xpath("(//input[@formcontrolname='Quantity'])[1]");
		By quantity2 = By.xpath("(//input[@formcontrolname='Quantity'])[2]");
		
		By unitPrice1 = By.xpath("(//input[@formcontrolname='UnitPrice'])[1]");
		By unitPrice2 = By.xpath("(//input[@formcontrolname='UnitPrice'])[2]");
		
		ComponentHandler.Click(addMoreService);
		ComponentHandler.Sleep(2);
		ComponentHandler.Send(inpConstructionCompanyName, "Liability Test Service");
		ComponentHandler.Sleep(0.5);
		ComponentHandler.Send(subitemTitle1, "Liability Subitem 1");
		ComponentHandler.Send(quantity1, "10");
		ComponentHandler.Send(unitPrice1, "15");
		ComponentHandler.Sleep(1);
		ComponentHandler.Click(addSubitem);
		ComponentHandler.Sleep(1.5);
		ComponentHandler.Send(subitemTitle2, "Liability Subitem 2");
		ComponentHandler.Send(quantity2, "20");
		ComponentHandler.Send(unitPrice2, "15");
		ComponentHandler.Sleep(1);
		ComponentHandler.Click("(//mat-radio-group[@id='am-1190']/mat-radio-button/label/div)[1]");
		ComponentHandler.Sleep(0.5);

		//Initially value should be null after enabling liability evaluation
		String liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		assert liabilityValue.length()==0 : "Liability not null initially";
		System.out.println("Liability value is initially null");
		ch.Send("//ng-select[@id='am-1189']/div/div/div/input", " ");
		ComponentHandler.Sleep(1);
		ch.Click("(//div[@role=\"option\"])[1]");

		//Liability value check after component selection
		liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		assert liabilityValue.equals("450") : "Liability calculation not matched";
		System.out.println("Liability value matched after component selection");
		ComponentHandler.ClearAndSend(age, "70");
		ComponentHandler.Sleep(2);

		//Liability evaluation after setting age for fair value less then 20%, fair value = (usefulLife - age) / usefulLife
		String liabilityComment = ComponentHandler.GetAttribute(liabilityAdditionalCommentForTwenty, "innerHTML");
		ComponentHandler.Sleep(0.5);
		assert liabilityComment.length()>0 : "Liability 20% comment not present";
		System.out.println("Found 20% comment when fair value percentage is < 20%");
		ComponentHandler.ClearAndSend(age, "10");
		ComponentHandler.Sleep(1);

		//Liability evaluation after setting age for fair value greater than 20%
		liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		assert liabilityValue.equals("390") : "Liability calculation not matched";
		System.out.println("Liability value matched after fair value > 20%");
		liabilityComment = ComponentHandler.GetAttribute(liabilityAdditionalCommentForTwenty, "innerHTML");
		ComponentHandler.Sleep(0.5);
		assert liabilityComment.length()<=0 : "Liability 20% comment not cleared after liability is set above 20%";
		System.out.println("Liability 20% comment is cleared after liability is set above 20%");
		ComponentHandler.Click(usefulLifeToggle);
		ComponentHandler.Sleep(0.5);
		ComponentHandler.ClearAndSend(usefulLife, "70");

		//Liability evaluation after manually enabling the useful life input
		liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		assert liabilityValue.equals("385.71") : "Liability calculation not matched after setting useful life manually";
		System.out.println("Liability value matched after manually setting useful life");
		ComponentHandler.Sleep(1);
		ComponentHandler.Click(newValueToggle);
		ComponentHandler.Sleep(0.5);
		ComponentHandler.ClearAndSend(newValue, "440");

		//liability evaluation after manually enabling New Value 
		liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		ComponentHandler.Sleep(1);
		assert liabilityValue.equals("377.14") : "Liability calculation not matched after setting useful life manually";
		System.out.println("Liability value matched after manually setting New Value");
		
		//Liability evaluation after setting negative age
		ComponentHandler.ClearAndSend(age, "-20");
		ComponentHandler.Sleep(0.5);
		liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		ComponentHandler.Sleep(1);
		assert liabilityValue.equals("565.71") : "Liability calculation not matched after setting negative value to age";
		System.out.println("Liability value matched after setting negative value to age");
		
		//Liability evaluation after setting negative useful life
		ComponentHandler.ClearAndSend(age, "10");
		ComponentHandler.Sleep(0.5);
		ComponentHandler.ClearAndSend(usefulLife, "-70");
		ComponentHandler.Sleep(0.5);
		ComponentHandler.ClearAndSend(newValue, "440");
		ComponentHandler.Sleep(0.5);
		liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		ComponentHandler.Sleep(1);
		assert liabilityValue.equals("502.86") : "Liability calculation not matched after setting negative value to useful life";
		System.out.println("Liability calculation matched after setting negative value to useful life");
		
		//Liability evaluation after setting negative NewValue
		ComponentHandler.ClearAndSend(usefulLife, "60");
		ComponentHandler.Sleep(0.5);
		ComponentHandler.ClearAndSend(newValue, "-400");
		liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		ComponentHandler.Sleep(1);
		assert liabilityValue.equals("-333.33") : "Liability calculation not matched after setting negative value to NewValue";
		System.out.println("Liability value matched after setting negative value to NewValue");
		
		//Liability evaluation after toggling new value
		ComponentHandler.Click(newValueToggle);
		ComponentHandler.Sleep(0.5);
		String newValueVal = ComponentHandler.GetAttribute(newValue, "value");
		ComponentHandler.Sleep(0.5);
		liabilityValue = ComponentHandler.GetAttribute(liabilityEvaluation, "value");
		ComponentHandler.Sleep(1);
		assert liabilityValue.equals("375") : "Liability calculation not matched after resetting NewValue using toggle";
		System.out.println("Liability value matched after resetting NewValue using toggle");
		assert newValueVal.equals("450") : "NewValue toggle didn't reset the value properly";
		System.out.println("NewValue toggle was able to reset the value properly");
		ComponentHandler.Click(btnSaveAll);
		ComponentHandler.Print("Case Create Successful=========");
		ComponentHandler.Sleep(8);
		return "375";
		
	}
	
	public void viewCaseDetailsAndValidateFinalLiabilityValue(String caseID, String liabilityValue, int serviceNo) {
		By serviceExpandBtn = By.id("expandService"+serviceNo);
		By caseDetailsLiabilityEvaluation = By.id("liabilityValue");
		//SmokeFix 1 comments next one added
		//ComponentHandler.Click("#additionalOptions");
		ch.Sleep(3);
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
		ComponentHandler.Sleep(3);
		ComponentHandler.Click("(//span[@id='caseNumber'])[1]");
		ComponentHandler.Sleep(5);
		ComponentHandler.Click(expandServiceList);
		ComponentHandler.Sleep(1.5);
		ComponentHandler.Click(serviceExpandBtn);
		ComponentHandler.Sleep(1.5);
		String liabilityEvaluationValueInCaseDetails = ComponentHandler.GetAttribute(caseDetailsLiabilityEvaluation, "innerHTML");
		assert ComponentHandler.IsMatched(liabilityValue, liabilityEvaluationValueInCaseDetails): "Liability value didn't match in case details";
		System.out.println("Liability value matched in case details");
	}
}