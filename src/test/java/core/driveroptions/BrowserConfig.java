package core.driveroptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserConfig {
	
	public static BROWSER_PLATEFORM Plateform = BROWSER_PLATEFORM.LOCAL;
	public static BROWSER_NAME Name = BROWSER_NAME.CHROME;
	
	// Browser Configurations..
	public static int ElementLoadTimeInSecond = 20;
	public static int PageLoadTimeInSecond = 120;
	public static boolean Headless = true;
	
	// REMOTE_URL is mandatory for BROWSER_PLATEFORM.REMOTE
	public static String REMOTE_URL = "http://browsers.thetestmart.com/wd/hub";
	
	
	/* This clause ensure the certifications of the browser. */
	private static DesiredCapabilities AcceptCertification() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return capabilities;
	}

	/* Browser control: and save password disable for login sites. */
	private static Map<String, Object> DisableSaveCredentials() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		return prefs;
	}

	/* Common Remote WebDriver Capabilities & VM Time-Slot Management. */
	@SuppressWarnings("serial")
	private static Map<String, Object> RemoteDriverCommonCapabilities() {
		return new HashMap<String, Object>() {
			{
				/* How to add test badge */
				put("name", "Test badge...");
				/* How to set session timeout */
				put("sessionTimeout", "15m");
				/* How to set timezone */
				put("env", new ArrayList<String>() {
					{
						add("TZ=UTC");
					}
				});
				/* How to add "trash" button */
				put("labels", new HashMap<String, Object>() {
					{
						put("manual", "true");
					}
				});
				/* How to enable video recording */
				put("enableVideo", true);
			}
		};
	}

	/* Browser control and personalization to access the restricted site. */
	public static ChromeOptions ChromeBrowserOptions() {
		ChromeOptions options = new ChromeOptions();
		//options.setCapability("browserVersion", "90.0");
		options.setCapability("moon:options", RemoteDriverCommonCapabilities());
		options.addArguments("start-maximized");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.merge(AcceptCertification());
		options.setExperimentalOption("prefs", DisableSaveCredentials());
		options.setHeadless(BrowserConfig.Headless);
		return options;
	}
	
	public static FirefoxOptions FirefoxBrowserOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("moon:options", RemoteDriverCommonCapabilities());
		options.merge(AcceptCertification());
		options.setHeadless(BrowserConfig.Headless);
		return options;
	}
	
	public static EdgeOptions EdgeBrowserOptions() {
		EdgeOptions options = new EdgeOptions();
		options.setCapability("moon:options", RemoteDriverCommonCapabilities());
		options.merge(AcceptCertification());
		return options;
	}
	
	public static InternetExplorerOptions IEBrowserOptions() {
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability("moon:options", RemoteDriverCommonCapabilities());
		options.merge(AcceptCertification());
		return options;
	}
	
	public static OperaOptions OperaBrowserOptions() {
		OperaOptions options = new OperaOptions();
		options.setCapability("moon:options", RemoteDriverCommonCapabilities());
		options.merge(AcceptCertification());
		return options;
	}
	
	public static SafariOptions SafariBrowserOptions() {
		SafariOptions options = new SafariOptions();
		options.setCapability("moon:options", RemoteDriverCommonCapabilities());
		options.merge(AcceptCertification());
		return options;
	}

}
