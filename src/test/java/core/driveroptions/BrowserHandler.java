package core.driveroptions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserHandler {

	public WebDriver SetDriver() {

		switch (BrowserConfig.Plateform) {
		case REMOTE:
			try {
				return new RemoteWebDriver(new URL("http://browsers.thetestmart.com/wd/hub"),
						BrowserConfig.ChromeBrowserOptions());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		case DOCKER:
			BrowserConfig.Headless = true;

		case LOCAL:
			switch (BrowserConfig.Name) {
			case CHROME:
				WebDriverManager.chromedriver().setup();
				return new ChromeDriver(BrowserConfig.ChromeBrowserOptions());

			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				return new FirefoxDriver(BrowserConfig.FirefoxBrowserOptions());

			case EDGE:
				WebDriverManager.edgedriver().setup();
				return new EdgeDriver(BrowserConfig.EdgeBrowserOptions());

			case IE:
				WebDriverManager.iedriver().setup();
				return new InternetExplorerDriver(BrowserConfig.IEBrowserOptions());

			case OPERA:
				WebDriverManager.operadriver().setup();
				return new OperaDriver(BrowserConfig.OperaBrowserOptions());

			case SAFARI:
				WebDriverManager.phantomjs().setup();
				return new SafariDriver(BrowserConfig.SafariBrowserOptions());

			default:
				System.out.println("Enter a correct Browser Name..!!!");
			}
			System.out.println(BrowserConfig.Name + "_BROWSER : Is Invoked & Set HEADLESS to : " + BrowserConfig.Headless);

		default:
			System.out.println("Enter a correct Plateform Name..!!!");
			return null;
		}
	}
}
