package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Module {
	
	//Parsing Date
	public static String parseDate(String format,String date) throws ParseException {
		SimpleDateFormat DateFor = new SimpleDateFormat(format);
		Date d = DateFor.parse(date);
		DateFor = new SimpleDateFormat("dd/MM/yyyy");
		return DateFor.format(d);
	}
	
	//Scroll to the particular WebElement
	public static void scroll(WebDriver driver, WebElement element) {
		try {
			System.out.println("Scrolling........");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			

		} catch (Exception e) {
			Reporter.log(
					"Exception in commonFindElement_GetAttribute for :"	+ e.getMessage());
		}
	}

}
