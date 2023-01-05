package Tests;

import java.text.ParseException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Utils.Module;
import Utils.Constants;

public class Wiki_Page {

	@FindBy(xpath="//input[@placeholder='Search Wikipedia']")
    WebElement searchBox;
	
	@FindBy(id="searchButton")
    WebElement searchButton;
	@FindBy(xpath="//div[text()='Release date']//following::li[1]")
    WebElement releaseDate;
	@FindBy(xpath="//th[text()='Country']//following::td[1]")
    WebElement country;
	
	
	
	public WebDriver driver;
	public WebDriverWait wait;
	public String Date_Wiki;
	public String Country_Wiki;
	public String path = "//div[text()='Release date']//following::li[1]";
	public Throwable e;
	
	
	public Wiki_Page(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		wait = new WebDriverWait(this.driver,5);
		PageFactory.initElements(this.driver, this);
	}
	
	
	
	public boolean Wiki_getData(){
		try {
		//Wait for Search Box to populate and Enter the Movie Name in Page
		 wait.until(ExpectedConditions.visibilityOf(searchBox));
		 searchBox.sendKeys(Constants.C_MovieName+" film");
		 searchButton.click();
		 
		 //Wait Movie Link is Displayed
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='mw-search-result-heading']//a)[1]"))).click();
		 Boolean bn = releaseDate.isDisplayed();
 
		 
		 if(bn) {
		//Scroll to Release Date	 
		
		 Module.scroll(driver, releaseDate);
		 Thread.sleep(2000);
		 
		 //Get the Country and Release Date
		 Date_Wiki = Module.parseDate("dd MMMM yyyy",releaseDate.getText().trim());
		 Country_Wiki = country.getText();
		 System.out.println("Date: "+Date_Wiki+" Country: "+Country_Wiki);
		 }
		 else {
			 System.out.println("Date or Country is not found in Wiki page,");
		 }
		 
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage()+" --- "+e.getCause());
			return false;
		 }
			
		
		return true;
	}

}
