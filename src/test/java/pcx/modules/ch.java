package pcx.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import core.components.ComponentHandler;
import core.components.ObjectRepository;

public class ch {
//	public static void Sleep(double timeInSeconds) {
//		ComponentHandler.Sleep(timeInSeconds);
//	}
//	public static void Send(String locator,String message) {
//		ComponentHandler.Send(locator, message);
//	}
//	public static void Click(String locator) {
//		ComponentHandler.Click(locator);
//	}
	
	
	public static WebElement makeLocator(String locator) {
		if(locator.startsWith("//") || locator.startsWith("(//")) {
			//System.err.println("xPath");
			return ObjectRepository.driver.findElement(By.xpath(locator)) ; 
		}
		else {
			//System.err.println("cssSelector");
			return ObjectRepository.driver.findElement(By.cssSelector(locator)) ; 
		}
		
	}
	
	public static void DoHighligt(String locator) {
		
		WebElement emailTxt =  makeLocator(locator);
		String oldStyle = ComponentHandler.GetAttribute(ComponentHandler.GetLocator(locator), "style"); 
		System.out.println("Old Style is [ "+oldStyle+" ] -----------");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) ObjectRepository.driver; 
		//jsExecutor.executeScript("arguments[0].style.border='2px solid red'", emailTxt);
		//jsExecutor.executeScript("arguments[0].style.background='red'", emailTxt);
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background-color:rgba(255,255,0,0.3) ; color: red')", emailTxt);
		Sleep(0.2);
		setStyle(locator, oldStyle);
		
	}
	
	public static void setStyle(String locator,String StyleCSS) {
		WebElement emailTxt =  makeLocator(locator);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) ObjectRepository.driver; 
		//jsExecutor.executeScript("arguments[0].style.border='2px solid red'", emailTxt);
		//jsExecutor.executeScript("arguments[0].style.background='red'", emailTxt);
		jsExecutor.executeScript("arguments[0].setAttribute('style', '"+StyleCSS+"')", emailTxt);
	}
	
	// ============================
	
	public static void Sleep(double timeInSeconds) {
		ComponentHandler.Sleep(timeInSeconds);
	}
	public static void Send(String locator,String message) {
		//Sleep(1);
		DoHighligt(locator);
		ComponentHandler.Send(locator, message);
		System.err.println("Send "+message+" on [ "+locator+" ]");
	}
	public static void Click(String locator) {
		//Sleep(1);
		DoHighligt(locator);

		ComponentHandler.Click(locator);
		
		System.out.println("Clicked on [ "+locator+" ]");
	}
	public static String byText(String text) {
		return  "(//span[text()='"+text+"'])[1]";
	}
	public static String byText(String text,String TagName) {
		return  "(//"+TagName+"[text()='"+text+"'])[1]";
	}
	
	public static void ClickByText(String text) {
		Click(byText(text));
	}
	public static void ClickByText(String text,String TagName) {
		Click(byText(text,TagName));
	}
	
	
	public static void sendAndSelect(String Locator,String Option) {
		Sleep(0.5);
		Send(Locator, "Ghorar Dim");
		ComponentHandler.ClearAndSend(Locator, Option);
		Sleep(2);
		Click("(//span[text()=' "+Option+" '])[1]");
		//Click("(//span[contains(text(), ' "+Option+" ')])[1]");
	}
	
	
}
