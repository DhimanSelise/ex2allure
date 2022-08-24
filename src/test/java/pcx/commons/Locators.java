package pcx.commons;

import org.openqa.selenium.By;

public class Locators {
	public static class AfterLogin{
		public final static String mailBox = "//div[contains(text(), ' Zurich ')]";
		public final static String nextMail = "[aria-label='Next page']";
		public final static String selectedMail = "(//mat-card[@id='am-1263'])[4]";
	}
	
	public static class createUser{
		public final static String btnUsers = "[href=\"/users\"]";
		public final static String btnCreatUser = "//span[text()='CREATE USER']";
		public final static String btnSalutation = "[formcontrolname=\"Salutation\"]";
		public final static String btnMr = "//span[text()='Mr.']";
		
		public final static String inpFirstName = "[formcontrolname=\"FirstName\"]";
		public final static String inpLastName = "[formcontrolname=\"LastName\"]";

		public final static String rdoGender = "//mat-radio-group[@formcontrolname=\"Gender\"]/mat-radio-button[2]/label/div[1]";
		public final static String optionRole = "[formcontrolname=\"Role\"]";
		public final static String RoleSelect = "//span[text()=' Inspector ']";
		public final static String inpEmail ="[formcontrolname=\"Email\"]" ;
		public final static String optionPhone = "//international-phone-number/div/span/div/button/span[2]";
		public final static String selectBD = "[class=\"flag flag-bd\"]";
		public final static String inputPhone ="[placeholder=\"Telephone(Personal)\"]" ;
	
		public final static String SaveUser = "[id='am-1318']";
		public final static String isUserExits = "//div[text()=' Email already exists ']" ;
		public final static String btnCancel = "//span[text()='CANCEL']";
		
	}
	
	public static class VerifyEmail{
		public final static String emailInput =  "[id='login']" ; 
		public final static String btnArrow =  "[title='Check Inbox @yopmail.com']" ; 
	}
	
	public static class CreateCase{
		public final static String btnCreateCase = "//span[text()='CREATE CASE']";
//		public final static String deleteServices = "(//button[@id='am-1175'])[1]";
		public final static String deleteServices = "#serviceMenu";
		public final static String inpInsuranceClaimNumber = "[formcontrolname='InsuranceClaimNumber']";
		public final static String inpZipCodeOfIssuer = "[formcontrolname='ZipCodeOfIssuer']";
		public final static String inpName =  "[formcontrolname='Name']";
		public final static String inpCompanyName =  "[formcontrolname='CompanyName']";
		public final static String inpEmailAddress =  "[formcontrolname='EmailAddress']";
		public final static String inpPhoneNumber =  "(//input[@placeholder='Phone'])[1]";
		public final static String btnSelectBD = "[class='flag flag-bd']" ; 
		public final static String btnCountryCodes = "//international-phone-number[@id='am-1168']//button[@class='dropbtn btn']";
		
		// Inspector Report
		
		public final static String rdoPrice =  "((//div[@id='am-1158'])[1]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]";
		public final static String rdoPlausibility1 =  "((//div[@id='am-1158'])[2]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]";
		public final static String rdoPlausibility2 =  "((//div[@id='am-1158'])[3]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]";
		public final static String rdoCompensation =  "((//div[@id='am-1158'])[4]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]";
		public final static String rdoLiability =  "((//div[@id='am-1158'])[5]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]";
		public final static String rdoRecommendedAction =  "((//div[@id='am-1158'])[6]/div/div/div/mat-radio-group/mat-radio-button)[2]/label/div[1]";
		
		public final static String commentPrice =  "(//textarea[@id='am-1161'])[1]";
		public final static String commentPlausibility1 =  "(//textarea[@id='am-1161'])[2]";
		public final static String commentPlausibility2 =  "(//textarea[@id='am-1161'])[3]";
		public final static String commentCompensation =  "(//textarea[@id='am-1161'])[4]";
		public final static String commentLiability =  "(//textarea[@id='am-1161'])[5]";
		public final static String commentRecommendedAction =  "(//textarea[@id='am-1161'])[6]";
		
		// Right Site
		public final static String inpAssignedBackoffice =  "[formcontrolname='AssignedBackOfficeSearchText']";
		public final static String rdoOfficierSelect =  "//mat-radio-group[@formcontrolname='BackofficeUserId']/div/div/div[2]/mat-radio-button/label/div[1]";
		public final static String inpAssignedInspector =  "[formcontrolname='AssignedInspectorSearchText']";
		public final static String rdoInspecterSelect =  "//mat-radio-group[@formcontrolname=\"InspectorUserId\"]/div/div/div[2]/mat-radio-button/label/div[1]";
		public final static String btnSaveAll =  "[id='am-1147']";

	}
	
}
