package core.components;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import core.driveroptions.BrowserConfig;
import core.driveroptions.BrowserHandler;

public class ComponentHandler {
	public static boolean Init() {
		ObjectRepository.driver = new BrowserHandler().SetDriver();
		ObjectRepository.action = new Actions(ObjectRepository.driver);
		ObjectRepository.js = (JavascriptExecutor) ObjectRepository.driver;
		ObjectRepository.wait = new WebDriverWait(ObjectRepository.driver, 30);
		ObjectRepository.project_path = System.getProperty("user.dir");
		return ObjectRepository.SetTimeOut();
	}

	// Setting Element/Elements Searching Time..............
	public static boolean SetTimeOut() {
		return SetTimeOut(BrowserConfig.ElementLoadTimeInSecond);
	}
	public static boolean SetTimeOut(int timeout_in_second) {
		ObjectRepository.driver.manage().timeouts().implicitlyWait(timeout_in_second, TimeUnit.SECONDS);
		return true;
	}
	// .....................................................
	
	
	
	// Getting Element/Elements Locator.....................
	public static By GetLocator(String locator) {
		if (locator.startsWith("//") || locator.startsWith("(//"))
			return By.xpath(locator);
		else
			return By.cssSelector(locator);
	}
	// .....................................................
	
	
	
	// Quick Checking of an Elements .......................
	public static WebElement GetElementQuick(String locator) {
		return GetElementQuick(GetLocator(locator));
	}
	public static WebElement GetElementQuick(By locator) {
		try {
			SetTimeOut(2);
			return ObjectRepository.driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("locator: ("+locator+") is not preasent.");
			return null;
		}finally {
			SetTimeOut();
		}
	}

	public static List<WebElement> GetElementsQuick(String locator) {
		return GetElementsQuick(GetLocator(locator));
	}
	public static List<WebElement> GetElementsQuick(By locator) {
		try {
			SetTimeOut(2);
			return ObjectRepository.driver.findElements(locator);
		} catch (Exception e) {
			System.out.println("locator: ("+locator+") is not preasent.");
			return null;
		}finally {
			SetTimeOut();
		}
	}
	// .....................................................
	
	
	
	// Getting Element/Elements.............................
	public static WebElement GetElement(String locator) {
		return GetElement(GetLocator(locator));
	}
	public static WebElement GetElement(By locator) {
		return ObjectRepository.driver.findElement(locator);
	}

	public static List<WebElement> GetElements(String locator) {
		return GetElements(GetLocator(locator));
	}
	public static List<WebElement> GetElements(By locator) {
		return ObjectRepository.driver.findElements(locator);
	}
	// .....................................................

	
	
	// Checking Presence of an Elements & It's Size.........
	public static boolean IsElementPresent(String locator) {
		return IsElementPresent(GetLocator(locator));
	}
	public static boolean IsElementPresent(By locator) {
		return GetElementsQuick(locator).size() > 0;
	}

	public static boolean IsElementDisplayed(String locator) {
		return IsElementDisplayed(GetLocator(locator));
	}
	public static boolean IsElementDisplayed(By locator) {
		return GetElementQuick(locator).isDisplayed();
	}

	public static boolean IsElementEnable(String locator) {
		return IsElementEnable(GetLocator(locator));
	}
	public static boolean IsElementEnable(By locator) {
		return GetElementQuick(locator).isEnabled();
	}

	public static boolean IsElementSelected(String locator) {
		return IsElementSelected(GetLocator(locator));
	}
	public static boolean IsElementSelected(By locator) {
		return GetElementQuick(locator).isSelected();
	}
	// .....................................................
	
	
	
	// Executing Script.....................................
	public static boolean ExecuteScript(String script) {
		return ExecuteScript(script, null);
	}
	public static boolean ExecuteScript(String script, WebElement element) {
		if(element==null) ObjectRepository.js.executeScript(script);
		else ObjectRepository.js.executeScript(script, element);
		return true;
	}
	// .....................................................
	
	
	
	// Scrolling Into Element...............................
	public static boolean ScrollIntoElement(String locator) {
		return ScrollIntoElement(GetLocator(locator));
	}
	public static boolean ScrollIntoElement(By locator) {
		if (IsElementPresent(locator))
			return ScrollIntoElement(GetElement(locator));
		return false;
	}
	public static boolean ScrollIntoElement(WebElement element) {
		return ExecuteScript("arguments[0].scrollIntoView();", element);
	}
	// .....................................................
	
	
	
	// Getting Element/Elements Attribute & Size...........
	public static int GetElementsSize(String locator) {
		return GetElementsSize(GetLocator(locator));
	}
	public static int GetElementsSize(By locator) {
		return GetElementsQuick(locator).size();
	}

	public static String GetElementAttribute(String locator, String attribute) {
		return GetElementAttribute(GetLocator(locator), attribute);
	}
	public static String GetElementAttribute(By locator, String attribute) {
		if (!ScrollIntoElement(locator))
			WaitForElementVisibility(locator, 5);
		return GetElement(locator).getAttribute(attribute);
	}
	// .....................................................
	
	
	
	// Wait for Element.....................................
	public static boolean WaitForElementVisibility(String locator, int time_out) {
		return WaitForElementVisibility(GetLocator(locator), time_out);
	}
	public static boolean WaitForElementVisibility(By locator, int time_out) {
		SetTimeOut(time_out/4 > 1 ? time_out/4 : 2);
		WebDriverWait wait = new WebDriverWait(ObjectRepository.driver, time_out);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception ex) {
			return false;
		} finally {
			SetTimeOut();
		}
	}

	public static boolean WaitForElementClickablity(String locator, int time_out) {
		return WaitForElementClickablity(GetLocator(locator), time_out);
	}
	public static boolean WaitForElementClickablity(By locator, int time_out) {
		SetTimeOut(time_out/4 > 1 ? time_out/4 : 2);
		WebDriverWait wait = new WebDriverWait(ObjectRepository.driver, time_out);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}finally {
			SetTimeOut();
		}
	}
	// .....................................................
	
	
	
	// Force Wait...........................................
	public static boolean Sleep(double time_in_second) {
		try {
			Thread.sleep((long) (1000 * time_in_second));
			return true;
		} catch (InterruptedException e) { return false;}
	}
	// .....................................................
	
	
	
	// Send & Clear.........................................
	public static boolean Send(String locator, String message) {
		return Send(GetLocator(locator), message);
	}
	public static boolean Send(By locator, String message) {
		if (!ScrollIntoElement(locator))
			WaitForElementVisibility(locator, 5);
		GetElement(locator).sendKeys(message);
		return true;
	}

	public static boolean Send(String locator, Keys key) {
		return Send(GetLocator(locator), key);
	}
	public static boolean Send(By locator, Keys key) {
		if (!ScrollIntoElement(locator))
			WaitForElementVisibility(locator, 5);
		GetElement(locator).sendKeys(key);
		return true;
	}

	public static boolean Clear(String locator) {
		return Clear(GetLocator(locator));
	}
	public static boolean Clear(By locator) {
		if (!ScrollIntoElement(locator))
			WaitForElementVisibility(locator, 5);
		GetElement(locator).clear();
		return true;
	}
	// .....................................................
	
	
	
	// Click, Quick-Click Or Nth-Click......................
	private static int click_count = 0;
	private static boolean click_tried = false;

	public static boolean Click(String locator) {
		return Click(GetLocator(locator));
	}
	public static boolean Click(By locator) {
		if (!ScrollIntoElement(locator) || !IsElementEnable(locator)) {
			if (click_tried || click_count > 1) {
				click_count = 0;
				click_tried = false;
				System.out.println("Enough tried..! Your locator ("+locator+") seems wrong, Please check your locator again..!");
				return false;
			}
			click_tried = WaitForElementClickablity(locator, 5);
			System.out.println(++click_count + " time click failed!! locator: ("+locator+"), Trying one's more...");
			Click(locator);
		}
		return Click(GetElement(locator));
	}
	public static boolean Click(WebElement element) {
		try {
			ExecuteScript("arguments[0].click();", element);
		} catch (Exception e) {
			try {
				element.click();
			} catch (Exception ex) {
				ObjectRepository.action.moveToElement(element).click().build().perform();
				System.out.println("We are tried to click in several way. Please check your locator again..!");
			}
		}
		return true;
	}

	public static boolean ClickNthElement(String common_locator, int index) {
		return ClickNthElement(GetLocator(common_locator), index);
	}
	public static boolean ClickNthElement(By common_locator, int index) {
		List<WebElement> items = GetElements(common_locator);
		ScrollIntoElement(items.get(index));
		return Click(items.get(index));
	}

	public static boolean ClickQuick(String locator) {
		return ClickQuick(GetLocator(locator));
	}
	public static boolean ClickQuick(By locator) {
		return Click(GetElementQuick(locator));
	}
	
	public static boolean PressKey(Keys key) {
		ObjectRepository.action.sendKeys(key).build().perform();
		return true;
	}
	// .....................................................
	
	
	
	// Browser Handle Methods...............................
	public static boolean BrowserMaximize() {
		ObjectRepository.driver.manage().window().maximize();
		return true;
	}
	public static boolean NavigateToURL(String url) {
		ObjectRepository.driver.get(url);
		return true;
	}
	public static boolean GoBack() {
		ObjectRepository.driver.navigate().back();
		return true;
	}
	public static boolean GoForward() {
		ObjectRepository.driver.navigate().forward();
		return true;
	}
	public static boolean RefreshPage() {
		ObjectRepository.driver.navigate().refresh();
		return true;
	}
	public static String GetTitle() {
		return ObjectRepository.driver.getTitle();
	}
	public static String GetURL() {
		return ObjectRepository.driver.getCurrentUrl();
	}
	// .....................................................
	
	
	
	// Window & Frame Switching.............................
	private static String parent_window;
	private static Iterator<String> windows;

	public static boolean SwitchToWindow(String window_handler) {
		ObjectRepository.driver.switchTo().window(window_handler);
		return true;
	}
	public static boolean SwitchToNextWindow() {
		parent_window = ObjectRepository.driver.getWindowHandle();
		windows = ObjectRepository.driver.getWindowHandles().iterator();

		if (windows.hasNext()) {
			ObjectRepository.driver.switchTo().window(windows.next());
			return true;
		}
		else {
			System.out.println("There is no next window to switch..!!!");
			return false;
		}
	}
	public static boolean SwitchToParentWindow() {
		ObjectRepository.driver.switchTo().window(parent_window);
		return true;
	}
	public static boolean SwitchToNthWindow(int n) {
		windows = ObjectRepository.driver.getWindowHandles().iterator();
		for (int i = 1; i < n; i++) {
			if (!windows.hasNext())
				return false;
			windows.next();
		}
		ObjectRepository.driver.switchTo().window(windows.next());
		return true;
	}

	public static boolean SwitchToFrame(String locator) {
		return SwitchToFrame(GetLocator(locator));
	}
	public static boolean SwitchToFrame(By locator) {
		ObjectRepository.driver.switchTo().frame(GetElement(locator));
		return true;
	}

	public static boolean PopupAccepting() {
		ObjectRepository.driver.switchTo().alert().accept();
		return true;
	}
	public static boolean PopupCanceling() {
		ObjectRepository.driver.switchTo().alert().dismiss();
		return true;
	}

	public static String PopupText() {
		return ObjectRepository.driver.switchTo().alert().getText();
	}
	// .....................................................
	
	
	// ================== Coded by Dhiman ======================
		public static void Print(String s) {
			System.out.println(s);
		}
		public static void Print(int s) {
			System.out.println(s);
		}
		
		public static int Rand(int min, int max) {
			return (int)(Math.random()*(max-min+1)+min);
		}
		public static int Rand(int max) {
			int min = 1;
			return (int)(Math.random()*(max-min+1)+min);
		}
		public static String Rand(int min, int max,String s) {
			int val =  (int)(Math.random()*(max-min+1)+min);
			return ""+val;
		}
		public static void ClearAndSend(By locator, String message) {
			Clear(locator);
			Sleep(0.5);
			Send(locator,message);
		}
		public static void ClearAndSend(String locator, String message) { // added by Dhiman
			Clear(locator);
			Sleep(0.5);
			Send(locator,message);
		}
		
		public static int GetSize(String CommonLocator) { // added by Dhiman
			return (ObjectRepository.driver.findElements(GetLocator(CommonLocator))).size();
		}
		
		public static String GetTextOfInput(String locator) { // added by Dhiman
			return ObjectRepository.driver.findElement(GetLocator(locator)).getAttribute("value");
		}
		public static String GetText(String locator) { // added by Dhiman
			return ObjectRepository.driver.findElement(GetLocator(locator)).getText();
		}
		
		
		public static String GetAttribute(String locator, String attribute) {
			return GetAttribute(GetLocator(locator), attribute);
		}
		public static String GetAttribute(By locator, String attribute) {
			if(!ScrollIntoElement(locator))
				return null;
			// Need to make a custom exception.
			return GetElement(locator).getAttribute(attribute);
		}
		
		//--------- For compare two string matched or not --------------------
		public static boolean IsMatched(String myString,String expectedString) {
			String a = myString.trim().toLowerCase() ; 
			String b = expectedString.trim().toLowerCase() ;
			System.out.println("Compared Strings are:\n"+a+"\n"+b);
			if(a.equals(b)) {
				Assert.assertEquals(true, true);
				System.out.println("Matched");
				return true;
			}
			else {
				System.out.println("Not Matched");
				Assert.assertEquals(false, true);
				return false;
			}
		}
		
		// ================== Dhiman's Code Ends ====================
}
