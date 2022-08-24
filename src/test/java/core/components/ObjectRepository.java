package core.components;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.driveroptions.BrowserConfig;

public class ObjectRepository {
	public static Actions action;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static String project_path;

	public static boolean SetTimeOut() {
		driver.manage().timeouts().implicitlyWait(BrowserConfig.ElementLoadTimeInSecond, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(BrowserConfig.PageLoadTimeInSecond, TimeUnit.SECONDS);
		return true;
	}
}
