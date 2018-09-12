package com.assignment.Baseutils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class BaseTest extends FrameworkBase {

	protected WebDriver driver;
	private static SoftAssert softAssert;
	
	/**
	 * method used to launch the browser.
	 * @return
	 */
	@BeforeSuite
	public WebDriver launchDriver() {
		try {
			if (config.getProperty("Browser").equals("firefox")) {
				final String driverPath = System.getProperty("user.dir") + "\\drivers\\geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", driverPath);
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
				final FirefoxProfile fp = new FirefoxProfile();
				fp.setPreference("security.mixed_content.block_active_content", false);
				fp.setPreference("security.mixed_content.block_display_content", true);
				fp.setPreference("browser.download.folderList", 2);
				fp.setPreference("browser.cache.disk.enable", false);
				fp.setPreference("browser.cache.memory.enable", false);
				fp.setPreference("browser.cache.offline.enable", false);
				fp.setPreference("network.http.use-cache", false);
				String dir = System.getProperty("user.dir");
				dir = dir + "\\src\\resources\\DownloadTemplate";
				fp.setPreference("browser.download.dir", dir);
				fp.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,"
								+ "application/x-excel,application/x-msexcel,application/excel,application/pdf,"
								+ "text/html,application/octet-stream,application/x-ms-excel,application/x-excel,application/x-dos_ms_excel,application/xls,application/x-xls,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				fp.setPreference("webdriver.load.strategy", "fast");
				final DesiredCapabilities dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, fp);
				dc.setCapability("marionette", false);
				driver = new FirefoxDriver(dc);
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
			} else if (config.getProperty("Browser").equals("chrome")) {
				final String driverPath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				final ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-web-security");
				options.addArguments("--allow-running-insecure-content");
				options.addArguments("--disable-extensions");
				options.addArguments("test-type");
				options.addArguments("--start-maximized");
				options.addArguments("--disable-web-security");
				options.addArguments("--allow-running-insecure-content");
				options.addArguments("--start-maximized");
				final Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
			}
		} catch (final Exception e) {
			log(" Caught Exception in the launching Chrome browser" + e);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(config.getProperty("ApplicationUrl"));

		return driver;
	}
	/**
	 * Soft assert to verify the expected to actual
	 * @return
	 */
	public SoftAssert getsoftAssert(){
		if(softAssert==null){
			softAssert=new SoftAssert();
			}
		return softAssert;
	}
	/**
	 * Quitting driver instance
	 */
	@AfterSuite
	public void quitDriver(){
		driver.quit();
	}
	
}
