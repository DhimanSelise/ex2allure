package pcx.commons;

import pcx.modules.ArrayAndString;

public class config {
	public static class Common{
		public static String address = "Selise Digital platforms";
	}
	public static class Default {
		public static String DefaultURL = URL.StageURL;
		public static String DefaultLanguage = "en";
		public static String DefaultDomain = "yopmail.com";
	}
	
	public static class URL {
		public static String DevURL = "http://clm.seliselocal.com/login";
		public static String TestURL = "https://stage-clmtest.selise.biz/login";
		public static String StageURL = "https://stage-clm.selise.biz/login";
	}
	
	public static class InspectorReport{
		public static String priceText = "Something about price";
		public static String plausibility1Text = "Plausibility comment 1" ; 
		public static String plausibility2Text = "Plausibility comment 2" ; 
		public static String LibalityText = "commentLiability" ; 
		public static String compensationText = "comment Compensation" ; 
		public static String recommendedActionText =  "Something Recommended" ; 
	}
	
	public static class CaseInfo{
		public static String issuerZip = "4500" ; 
		public static String name = "Responsible Person Name" ; 
		public static String companyName = "Selise BD" ; 
		public static String emailAddress = "anemail@yopmail.com" ; 
		public static String phoneNumber = "+8801684216333" ; 
		
		public static String caseType = "a" ;
		public static String constructionCompanyName = "A Company Name" ; 
		public static String constructionCompanyConactPerson = "Resp Person name" ;
		public static String constructionCompanyConactEmail = "conatct@companyname.com" ;  
		public static String constructionCompanyConactPhone = "+8801521445723" ;  
		
	}
	
	public static class NewUserInfo{
		public static String phoneNo = "1840929999";
		public static String email = "testuser" ; 
		public static String firstName = "Dhiman" ; 
		public static String lastName = "Sarker" ; 
		public static String pass = "1qazZAQ!" ; 
		
	}
	
	public static class UpdatedUserInfo{
		public static String updatedFirstName = "Robin" ; 
		public static String updatedLastName = "Bhaiya" ; 
	}
	
	public static class ReturnedData{
		public static String title = "" ; 
		public static int [] itemAndSubItemList = {2,1} ;
		public static ArrayAndString data = new ArrayAndString(); 
//		data.itemAndSubItemList = {1,2}; 
//		data.caseTitle = "data";
	}
	
	public static class StatusUpdate{
		public static String email = "saniu1@yopmail.com" ; 
	}
	public static class DeleteExistingUser{
		public static String email = "testuser2954669@yopmail.com" ;
	}
	
	public static class AdditionalOptions{
		public static String InsuranceProviderName = "Zurich" ; 
		public static String CaseStatus = "Audit Phase" ;
		public static String AssignedInspector = "IPEX Staging" ;
		public static String CaseName = "Delete" ;
	}
	
	public static class ApplitoolsEyes{
		public static String EyesApiKeyForIPEX = "ptGOuJ3NRt107zr110mpi43stSAxV107Muis97FX4mQ103uFGdMg110";
	}
}
