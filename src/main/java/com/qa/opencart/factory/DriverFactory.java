package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author SouravGhosh
 * 
 */

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tLDriver = new ThreadLocal<WebDriver>();

	/**
	 * This methods will return the driver
	 * 
	 * @param browser
	 * @return
	 */

	public WebDriver init_driver(Properties prop) {
		String browser = prop.getProperty("browser").trim();
		System.out.println("browser name is :" + browser);

		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			}else {
				tLDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

			}
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			///tLDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tLDriver.set(new EdgeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			}else {
				tLDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

			}
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			// tLDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				
		
		
		} else {
			System.out.println("please pass the right browser..." + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	
	
	private void init_remoteDriver(String browserName) {
	    System.out.println("Running test on remote with browser: " + browserName);

	    if (browserName.equalsIgnoreCase("chrome")) {
	        //DesiredCapabilities cap = DesiredCapabilities.chrome();
	       // cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
	    	 ChromeOptions chromeOptions = optionsManager.getChromeOptions();
	    	// chromeOptions.setCapability("browserName", "chrome");
	    	// chromeOptions.setCapability("browserVersion",browserVersion);
	    	// chromeOptions.setCapability("enableVNC",true);
	    	 
	    	 
	    	 try {
	            tLDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), chromeOptions));
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to initialize remote WebDriver", e);
	        }
	    
	}else if
	 (browserName.equalsIgnoreCase("firefox")) {
	       // DesiredCapabilities cap = DesiredCapabilities.chrome();
	       // cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
		 FirefoxOptions firefoxOptions = optionsManager.getFirefoxOptions();
	        try {
	            tLDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), firefoxOptions));
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to initialize remote WebDriver", e);
	        }
	    }
	}


	public static synchronized WebDriver getDriver() {
		return tLDriver.get();
	}
	
	
	
	
	/**
	 * This method initialized properties
	 * 
	 * @return
	 */

//	public Properties init_prop() {
//		prop = new Properties();
//
//		FileInputStream ip = null;

//		String env = System.getProperty("env");
//        if(env==null) {
//        	System.out.println("Running on Enviorment :PROD Env..");
//		try {
//			ip = new FileInputStream("./src/test/resources/config/config.properties");
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} 
//		}
//	else {
//			System.out.println("Running on Enviorment:"+env);
//			try {
//			switch(env) {
//			case"qa":
//				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
//				break;
//				
//			case"dev":
//				ip = new FileInputStream("./src/test/resources/config/dev.qa.properties");
//				break;
//			case"stage":
//				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
//				break;
//				default:
//					break;
//					
//				
//		}
//		}
//		catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
//			try {
//				prop.load(ip);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		try {
//			prop.load(ip);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//		return prop;
//}
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");

		try {
			if (env == null) {
				System.out.println("Running on Environment: PROD");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				System.out.println("Running on Environment: " + env);
				switch (env.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.qa.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				default:
					System.out.println("Invalid environment specified: " + env);
					throw new RuntimeException("Invalid environment: " + env);
				}
			}

			if (ip != null) {
				prop.load(ip);
			} else {
				throw new RuntimeException("Property file input stream is null.");
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load properties file.");
		}

		return prop;
	}
	
	/**
	 * 
	 */
	public String getScreenshot() {
		
		File srcFile= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	    String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
	    File destination =new File(path);
	    try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return path;
	}
	

}
