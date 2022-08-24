package pcx.modules;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;

import core.components.ComponentHandler;
public class SelectMail {
	private By nextPage = By.className("mat-paginator-navigation-next");
	private By deleteEmail = By.id("deleteEmail");
	private By deleteModalCloseBtn = By.id("am-1369");
	private By deleteModalConfirmBtn = By.id("am-1370");
	
	public void selectMail(String insuranceProvider, int mailIndex) {
		int mailNoOfPage = mailIndex % 10;
		By selectedMail = By.id("am-1263");
		selectInsuranceProvider(insuranceProvider);
		if(mailIndex>9) {
			this.navigateToSelectedMailPage(mailIndex);
		}
		ComponentHandler.Sleep(1);
		ComponentHandler.ClickNthElement(selectedMail, mailNoOfPage);
		ComponentHandler.Sleep(1);
	}
	public void navigateToSelectedMailPage(int mailIndex) {
		System.out.println("Navigating to page containing target email");
		int pageNo = 0, targetPageNo = mailIndex/10;
		while(pageNo<targetPageNo) {
			ComponentHandler.Sleep(0.7);
			ComponentHandler.Click(nextPage);
			ComponentHandler.Sleep(0.7);
			pageNo++;
		}
	}
	
	public void selectInsuranceProvider(String insuranceProvider) {
		By provider = By.xpath("//div[contains(text(), '"+ insuranceProvider +"')]");
		ComponentHandler.Click(provider);
		ComponentHandler.Sleep(3);
	}
	
	public void populateMailbox(String insuranceProvider, int totalMail) {
		selectInsuranceProvider(insuranceProvider);
		if(totalMail>9) {
			this.navigateToSelectedMailPage(totalMail);
		}
	}
	
	public void filterDepartment(String insuranceProvider, String department) {
		selectInsuranceProvider(insuranceProvider);
		By filterButton = By.xpath("//div[@class='h-56 pt-8 pb-8 ']/button[@id='am-1269']");
		By departmentButton = By.xpath("//div[@class='mat-radio-label-content' and contains(text(), '" + department + "')]");
		By applyFilter = By.id("am-1278");
		ComponentHandler.Click(filterButton);
		ComponentHandler.Sleep(0.5);
		ComponentHandler.Click(departmentButton);
		ComponentHandler.Sleep(0.5);
		ComponentHandler.Click(applyFilter);
		ComponentHandler.Sleep(2);
	}
	
	public void filterDepartmentValidation() { // added by DSB
		
		String noMailStatus = ComponentHandler.GetText("[class='no-email-text']") ; 
		String selectedDepartmentName = ComponentHandler.GetText("(//mat-icon[text()='filter_list']/following-sibling::span)[1]") ;
		
		if(noMailStatus.equals("No Data Found!")) {
			System.err.println("No Data Found! for "+selectedDepartmentName);
			return;
		}
		
		String totalMail  = ComponentHandler.GetText("[class='mat-paginator-range-label']") ; 
		System.err.println(totalMail);
		int numberOfShowingMail  = ComponentHandler.GetSize("[id='am-1263']") ; 
		Set<String> hash_Set = new HashSet<String>();
		
		 
		String departmentNameInMail = null; 
		for(int i=1 ; i<= numberOfShowingMail ; i++) {
			departmentNameInMail = ComponentHandler.GetText("[id='am-1265']") ; 
			hash_Set.add(departmentNameInMail);
			System.out.println(i+".Now found department tag : "+departmentNameInMail);
		}
		System.out.println(hash_Set);
		System.out.println("Set Size = "+hash_Set.size()+" | Showing Mail : "+numberOfShowingMail);
		System.out.println(hash_Set);
		
		if(hash_Set.size()==1 && departmentNameInMail != null && selectedDepartmentName.trim().toLowerCase().equals(departmentNameInMail.trim().toLowerCase())){
			System.out.println("All condition passed ! Validation Successful");
			Assert.assertEquals(false, false);
		
		}
		else {
			System.err.println("Validation not passed");
			Assert.assertEquals(false, true);
		}
		
		
		
	}
	
	
	public void deleteMail(String insuranceProvider, int mailIndex) {
		selectMail(insuranceProvider, mailIndex);
		ComponentHandler.Sleep(2);
		ComponentHandler.Click(deleteEmail);
		ComponentHandler.Sleep(5);
		if(ComponentHandler.IsElementPresent(deleteModalCloseBtn) || ComponentHandler.IsElementPresent(deleteModalConfirmBtn)) {
			assert ComponentHandler.IsElementPresent(deleteModalConfirmBtn) : "Please delete cases created from this mail first";
			ComponentHandler.Click(deleteModalConfirmBtn);
			ComponentHandler.Sleep(6);
			System.out.println("Delete Email Successful");
		} else {
			ComponentHandler.Sleep(6);
			if(ComponentHandler.IsElementPresent(deleteModalCloseBtn) || ComponentHandler.IsElementPresent(deleteModalConfirmBtn)) {
				assert ComponentHandler.IsElementPresent(deleteModalConfirmBtn) : "Please delete cases created from this mail first";
				ComponentHandler.Click(deleteModalConfirmBtn);
				ComponentHandler.Sleep(6);
				System.out.println("Delete Email Successful");
			} else {
				assert false : "Something went wrong, please try again!";
			}
		}
	}
}
