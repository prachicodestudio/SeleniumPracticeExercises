package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	WebDriver d;
	public DashboardPage(WebDriver driver)
	{
		d=driver;
		PageFactory.initElements(d, this);
	}
	
	public String getPageTitle()
	{
		return(d.getTitle());
	}
	
}
