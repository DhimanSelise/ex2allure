package packages.common.handlers;

import org.openqa.selenium.By;

import core.components.ComponentHandler;

public class CalendarHandler {
	private static By openCalendar = By.cssSelector("[aria-label='Open calendar']");
	
	public static void selectDates(String end_date) {
		selectDates("today", end_date);
	}
	public static void selectDates(String start_date, String end_date) {
		selectStartDate(start_date);
		selectEndDate(end_date);
	}
	public static void selectStartDate(String start_date) {
		ComponentHandler.ClickNthElement(openCalendar, 0);
		selectDate(start_date);
	}
	public static void selectEndDate(String end_date) {
		ComponentHandler.ClickNthElement(openCalendar, 1);
		selectDate(end_date);
	}
	
	private static void selectDate(String date) 
	{
		if(date.contains("today"))
			ComponentHandler.Click(".mat-calendar-body-today");
		else {
			String dayMonthYear[] = date.split(" ");
			ComponentHandler.Click("button[aria-label='Choose month and year']");
			ComponentHandler.Click(getLocator(dayMonthYear[2].trim()));
			ComponentHandler.Click(getLocator(dayMonthYear[1].trim().substring(0, 3).toUpperCase()));
			ComponentHandler.Click(getLocator(dayMonthYear[0].trim()));
		}
    }
	private static String getLocator(String label) {
		return "//div[text()='"+ label +"']";
	}
}
