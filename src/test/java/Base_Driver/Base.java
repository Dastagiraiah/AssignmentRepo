package Base_Driver;

import Utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;


public class Base {
  public WebDriver driver;
  @BeforeSuite
  public void beforeSuite() {
	  
	  if(driver==null) {
		  if(Constants.C_Browser.equalsIgnoreCase("Chrome")) {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  }
		  else if(Constants.C_Browser.equalsIgnoreCase("Edge")) {
			  WebDriverManager.edgedriver().setup();
			  driver = new EdgeDriver();
		  }
		  else if(Constants.C_Browser.equalsIgnoreCase("Firefox")) {
			  WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
		  }
		  else if(Constants.C_Browser.equalsIgnoreCase("Safari")) {
			  WebDriverManager.safaridriver().setup();
			  driver = new SafariDriver();
		  }
		  else
			  System.out.println("no browser setup");
	  }
	  
	  
  }

  @AfterSuite
  public void afterSuite() {
	  driver.quit();
  }

}
