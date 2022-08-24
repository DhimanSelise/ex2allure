package packages.common.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.components.ComponentHandler;
import core.components.ObjectRepository;
import packages.common.enums.DAYS;
import packages.common.enums.SLOT;

public class ScheduleHandler {
	
	public static void SelectSlot(SLOT slot) {
		ComponentHandler.Click("mat-select[name='SLOT']");
		ComponentHandler.Click("//span[@class='mat-option-text' and contains(text(),'"+10*(slot.ordinal()+1)+" Mins')]");
	}
	
	public static void SelectWorkingHours(DAYS day, String[] start_end_times) {
		ComponentHandler.Click("//*[ @class='mat-checkbox-label' and contains(text(), '"+day+"')]");
		ComponentHandler.WaitForElementVisibility("//div[ @class='ng-star-inserted' and contains(text(), '"+day+"')]", 30);
		int day_in_number = day.ordinal()+1;
		WebElement scope = ObjectRepository.driver.findElement(By.cssSelector("mat-expansion-panel:nth-child("+day_in_number+")"));
		int count=0;
		for(String time : start_end_times) {
			if(scope.findElement(By.cssSelector(".mat-button.mat-button-base.ng-star-inserted[color='primary']")).isEnabled())
				scope.findElement(By.cssSelector(".mat-button.mat-button-base.ng-star-inserted[color='primary']")).click();
			for(int i=0; i<2; i++) {
				ComponentHandler.Click(scope.findElements(ComponentHandler.GetLocator("mat-select")).get(count++));
				ComponentHandler.Sleep(1);
				ComponentHandler.Click("//span[@class='mat-option-text' and contains(text(), '"+time.split("-")[i].trim()+"')]");
				ComponentHandler.Sleep(1);
			}
		}
	}
}
