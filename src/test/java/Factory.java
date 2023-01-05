import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base_Driver.Base;
import Tests.IMDB_Page;
import Tests.Wiki_Page;
import Utils.Constants;

public class Factory extends Base {
	public String Date_Wiki;
	public String Country_Wiki;
	public String Date_IMDB;
	public String Country_IMDB;

	@Test
	public void IMDB_Test()  {
		driver.get(Constants.C_IMDB_Link);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		boolean b=false;
		IMDB_Page pg = new IMDB_Page(driver);
		try {
		b = pg.IMDB_getData();
		}
		catch (Exception e) {
			System.out.println("Error in IMDB Page");
			e.printStackTrace();
		}
		if(b==true) {
		this.Date_IMDB = pg.Date_Imdb;
		this.Country_IMDB = pg.Country_Imdb;
		}
		else
			Assert.fail("Exception in  IMDB page");
		
			
	}

	@Test
	public void Wiki_Test() {
		driver.get(Constants.C_Wiki_Link);
		//Thread.sleep(1000);
		
		Wiki_Page pg = new Wiki_Page(driver);
		boolean b=false;
		try {
			b = pg.Wiki_getData();
			}
			catch (Exception e) {
				System.out.println("Error in Wiki Page");
				e.printStackTrace();
			}
		
		if(b==true) {
			this.Date_Wiki = pg.Date_Wiki;
			this.Country_Wiki = pg.Country_Wiki;
		}
		else
			Assert.fail("Exception in  Wiki page" );	

	}
	
	@Test
	public void compareData() {
		Assert.assertEquals(Date_Wiki, Date_IMDB,"Both dates aren't matching");
		Assert.assertEquals(Country_Wiki, Country_IMDB,"Both country names aren't matching");
		
	}
}
