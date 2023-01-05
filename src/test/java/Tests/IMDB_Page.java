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
import Utils.*;
import Utils.Module;

public class IMDB_Page {
	
	@FindBy(xpath="//input[contains(@placeholder,'Search')]")
    WebElement searchBox;
	
	@FindBy(xpath="//div[contains(text(),'"+Constants.C_MovieName+"')]")
    WebElement selectMovie;
	
	@FindBy(xpath="//h1[@data-testid='hero-title-block__title']")
    WebElement movieTitile;
	
	@FindBy(xpath="//a[contains(text(),'Release date')]//following::a[1]")
    WebElement releaseDate;
	@FindBy(xpath="//button[contains(text(),'Country of origin')]//following::a[1]")
    WebElement country;
	

	public WebDriver driver;
	public WebDriverWait wait;
	public String Date_Imdb;
	public String Country_Imdb;
	Throwable e;
	
	public IMDB_Page(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		wait = new WebDriverWait(this.driver,5);
		PageFactory.initElements(this.driver, this);
	}
	
	public boolean IMDB_getData() {
		
		try {
			//Wait for Search Box to populate and Enter the Movie Name in Page
			WebElement elementSearchBox = wait.until(ExpectedConditions.visibilityOf(searchBox));
			elementSearchBox.sendKeys(Constants.C_MovieName);
		 	WebElement elementSelectMovie = wait.until(ExpectedConditions.visibilityOf(selectMovie));
		 	elementSelectMovie.click();
		 	
		 	//Wait for Movie title and Details Section to display in Page
		 	wait.until(ExpectedConditions.visibilityOf(movieTitile));
		 	WebElement details = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Details']")));
		 	Thread.sleep(3000);
		 	
		 	//Scroll to Details Section in Page
		 	Module.scroll(driver, details);
		 	Boolean b = releaseDate.isDisplayed();
		 	if(b) {
		 		//get the Release Date and Country
		 		String date = releaseDate.getText().split("\\(")[0].trim().replaceAll(",", "");
		 		Country_Imdb = country.getText().trim();
		 		Date_Imdb = Module.parseDate("MMMM dd yyyy", date);
		 		System.out.println("Date: "+Date_Imdb+" Country: "+Country_Imdb);
		 	}
		 	else
		 		System.out.println("Date or Country is not found in IMDB page");
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage()+" --- "+e.getCause());
			return false;
		}
		return true;
		
	}
	

}
