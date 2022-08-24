package pcx.modules;

import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;

import core.components.ComponentHandler;
import pcx.commons.config;

public class AdditionalOptions {
	
	// ------------ Helper Functions ---------------
	public void GotoCaseList() {
		ch.ClickByText("Cases");
		ch.Sleep(2);
	}
	public void ClickOnAdditonalOptionButton() {
		ch.Sleep(3);
		ch.ClickByText("expand_more", "mat-icon" );
		ch.Sleep(1);
		ch.Click("#am-1080"); // Clear button
		ch.Sleep(1);
		ch.ClickByText("expand_more", "mat-icon" );
		ch.Sleep(1);
	}
	public int GetNumberOfCases() {
		return ComponentHandler.GetSize("//datatable-body-row") ; 
	}
	
	public void filterValidation(String comonLocator) { // added by DSB
		System.err.println("___________ Validation Started _________");
		
		String noMailStatus = null;
		System.err.println("Please wait 10 seconds for findings...");
		if(ComponentHandler.IsElementPresent("[class='empty-row ng-star-inserted']")) {
			noMailStatus = ComponentHandler.GetText("[class='empty-row ng-star-inserted']") ;
		}
		 
		if(noMailStatus!=null) {
			System.err.println("No Data Found!");
			return;
		}
		
		String totalMail  = ComponentHandler.GetText("[class='mat-paginator-range-label']") ; 
		System.err.println(totalMail);
		
		int numberOfShowingCase  = GetNumberOfCases() ; 
		Set<String> hash_Set = new HashSet<String>();
		
		 
		String departmentNameInMail = null; 
		for(int i=1 ; i<= numberOfShowingCase ; i++) {
			departmentNameInMail = ComponentHandler.GetText("("+comonLocator+")["+i+"]") ; 
			hash_Set.add(departmentNameInMail);
			System.out.println(i+".Now found : "+departmentNameInMail);
		}
		System.out.println(hash_Set);
		System.out.println("Set Size = "+hash_Set.size()+" | Showing Case : "+numberOfShowingCase);
		System.out.println(hash_Set);
		
		
		if(hash_Set.size()==1){
			System.out.println("All condition passed ! Validation Successful");
			Assert.assertEquals(false, false);
		}
		else {
			System.err.println("Validation not passed");
			Assert.assertEquals(false, true);
		}
	}
	
	
	public void ValidationFunction(String FileterLocator,String FilterOptionValue,String commonLocator) {
		GotoCaseList();
		ch.Sleep(3);
		ClickOnAdditonalOptionButton();
		ch.Click(FileterLocator);
		ch.Sleep(0.5) ; 
		ch.ClickByText(" "+FilterOptionValue+" ");
		ch.Click("#am-1081"); // Apply Filter 
		//String commonInsuranceProviderLocator = "//datatable-body-row/div/datatable-body-cell[3]/div/div/div/span" ; 
		filterValidation(commonLocator);
	}
	
	
	//------------- Main Functions -----------------
	public void ValidateInsuranceProvider(String InsuranceProviderName) {
		GotoCaseList();
		ch.Sleep(3);
		ClickOnAdditonalOptionButton();
		ch.Click("[formcontrolname='InsuranceCompany']");
		ch.Sleep(0.5) ; 
		ch.ClickByText(" "+InsuranceProviderName+" ");
		ch.Click("#am-1081"); // Apply Filter 
		String commonInsuranceProviderLocator = "//datatable-body-row/div/datatable-body-cell[3]/div/div/div/span" ; 
		filterValidation(commonInsuranceProviderLocator);
	}
	
//	public void ValidateCaseStatus(String Status) {
//		GotoCaseList();
//		ch.Sleep(3);
//		ClickOnAdditonalOptionButton();
//		ch.Click("[formcontrolname='CaseStatus']");
//		ch.Sleep(0.5) ; 
//		ch.ClickByText(" "+Status+" ");
//		ch.Click("#am-1081"); // Apply Filter 
//		String commonCaseStatusLocator = "//datatable-body-row/div/datatable-body-cell[7]/div/div/div/div/span" ; 
//		filterValidation(commonCaseStatusLocator);
//	}
	
	public void ValidateCaseStatus(String Status) {
		String FileterLocator="[formcontrolname='CaseStatus']";
		String commonLocator="//datatable-body-row/div/datatable-body-cell[7]/div/div/div/div/span";
		ValidationFunction(FileterLocator,Status,commonLocator);
	}
	
	public void ValidateAssignedInspector(String InspectorName) {
		String FileterLocator="[formcontrolname='InspectorUserId']";
		String commonLocator="//datatable-body-row/div/datatable-body-cell[5]/div/div/button/div/div";
		ValidationFunction(FileterLocator,InspectorName,commonLocator);
	}
	
	public void ValidateCaseID(String CaseName) {
		
		
		GotoCaseList();
		ch.Sleep(3);
		ClickOnAdditonalOptionButton();
		ch.Send("[formcontrolname='ClaimNumber']",CaseName);
		ch.Sleep(0.5) ; 
		ch.Click("#am-1081"); // Apply Filter 
		

		String commonCaseIDLocator="//datatable-body-row/div/datatable-body-cell[1]/div/div/div/span";
		System.err.println("___________ Validation Started _________");
		
		String noMailStatus = null;
		System.err.println("Please wait 10 seconds for findings...");
		if(ComponentHandler.IsElementPresent("[class='empty-row ng-star-inserted']")) {
			noMailStatus = ComponentHandler.GetText("[class='empty-row ng-star-inserted']") ;
		}
		 
		if(noMailStatus!=null) {
			System.err.println("No Data Found!");
			return;
		}
		
		String totalMail  = ComponentHandler.GetText("[class='mat-paginator-range-label']") ; 
		System.err.println(totalMail);
		
		int numberOfShowingCase  = GetNumberOfCases() ; 
		Set<String> hash_Set = new HashSet<String>();
		
		 
		String departmentNameInMail = null; 
		
		boolean containsValidation = true ; 
		
		for(int i=1 ; i<= numberOfShowingCase && containsValidation ; i++) {
			departmentNameInMail = ComponentHandler.GetText("("+commonCaseIDLocator+")["+i+"]") ; 
			hash_Set.add(departmentNameInMail);
			System.out.println(i+".Now found : "+departmentNameInMail);
			containsValidation = departmentNameInMail.contains(config.AdditionalOptions.CaseName) ; 
			if(containsValidation==false) {
				System.out.println(CaseName+" is not found in case no:"+i+" ["+departmentNameInMail+"]");
			}
		}
		System.out.println(hash_Set);
		System.out.println("Set Size = "+hash_Set.size()+" | Showing Case : "+numberOfShowingCase);
		System.out.println(hash_Set);
		
		if(containsValidation) {
			Assert.assertEquals(CaseName, CaseName); 
		}
		else {
			Assert.assertEquals(departmentNameInMail, CaseName); 
			//ComponentHandler.IsMatched(CaseName, "Something Else") ; 
		}
		
		
	}
	
}
