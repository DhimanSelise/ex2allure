package pcx.modules;

import org.assertj.core.api.Assert;
import org.openqa.selenium.By;

import core.components.ComponentHandler;
import pcx.modules.ch;

public class CaseList {
	private By cases = By.xpath("//span[contains(@class, 'nav-link-title') and contains(text(), 'Cases')]");
	private By goToNextPage = By.cssSelector("[aria-label='go to next page']");
	private By exportExcelSpinner = By.id("am-1070");
	private By exportExcelButton = By.id("am-1071");
	private By assignInspectorButton = By.xpath("//button[contains(@class, 'inspector-selector')]");
	private String assignInspectorSearchText = "[formcontrolname='AssignedInspectorSearchText']";
	private By caseRows = By.id("caseNumber");
	private By deleteCase = By.id("am-1002");
	private By deleteConfirmBtn = By.id("am-1370");
	private By orderStatus = By.id("orderStatusToggle");

	public void navigateToCaselist() {
		ComponentHandler.Click(cases);
		ComponentHandler.Sleep(5);
	}

	public void paginateToCasePage(int caseIndex) {
		if (caseIndex > 0) {
			int currentPage = 0, targetPage = caseIndex / 10;
			ComponentHandler.WaitForElementClickablity(goToNextPage, 5000);
			while (currentPage < targetPage) {
				ComponentHandler.Click(goToNextPage);
				System.out.println("Page " + currentPage);
				ComponentHandler.Sleep(1.5);
				currentPage++;
			}
		}
	}

	@SuppressWarnings("unused")
	public void downloadExcelReport() {
		ComponentHandler.Click(exportExcelButton);
		ComponentHandler.Sleep(15);
		System.out.println("Waited for 15 seconds");
		if (ComponentHandler.IsElementPresent("#am-1070")) {
			ComponentHandler.Sleep(30);
			System.out.println("Waited for 45 seconds");
			if (ComponentHandler.IsElementPresent("#am-1070")) {
				ComponentHandler.Sleep(60);
				System.out.println("Waited for 1 minute and 45 seconds");
				if (ComponentHandler.IsElementPresent("#am-1070")) {
					ComponentHandler.Sleep(240);
					System.out.println("Waited for 5 minute and 45 seconds");
					if (ComponentHandler.IsElementPresent("#am-1070")) {
						ComponentHandler.Sleep(300);
						System.out.println("Waited for 10 minute and 45 seconds");
						if (ComponentHandler.IsElementPresent("#am-1070")) {
							assert false : "Something went wrong while downloading excel report";
						} else {
							System.out.println("Export Excel successful");
						}
					} else {
						System.out.println("Export Excel successful");
					}

				} else {
					System.out.println("Export Excel successful");
				}
			} else {
				System.out.println("Export Excel successful");
			}
		} else {
			System.out.println("Export Excel successful");
		}
	}

	public void changeAssignedInspector(String inspectorUsername, int caseIndexOfPage) {
		String inspectorUser = "//div[contains(@class,'inspectorProfilePicture')]//span[contains(text(), '"
				+ inspectorUsername + "')]";
		System.out.println("Case Index: "+caseIndexOfPage);
		ComponentHandler.ClickNthElement(assignInspectorButton, caseIndexOfPage);
		ch.Sleep(0.5);
		ch.Send(assignInspectorSearchText, inspectorUsername);
		ch.Sleep(2.5);
		ch.Click(inspectorUser);
		ch.Sleep(5);
		ComponentHandler.ClickNthElement(assignInspectorButton, caseIndexOfPage);
		ch.Sleep(0.5);
		String currentInspectorName  = ComponentHandler.GetText("//mat-icon[text()='check']//parent::div/div/span") ; 
		ComponentHandler.IsMatched(currentInspectorName, inspectorUsername) ; 
		
	}
	
	public void validateChangedInspector(int caseIndexOfPage) {
		;
	}

	public void clickCase(int caseIndexOfPage) {
		ComponentHandler.ClickNthElement(caseRows, caseIndexOfPage + 1);
		ComponentHandler.Sleep(6);
	}

	public void deleteCase() {
		ComponentHandler.Click(deleteCase);
		ComponentHandler.Sleep(2);
		ComponentHandler.Click(deleteConfirmBtn);
		ComponentHandler.Sleep(8);
	}
	
	public void changeOrderStatus() {
		ComponentHandler.Click(orderStatus);
		ComponentHandler.Sleep(5);
	}
}
