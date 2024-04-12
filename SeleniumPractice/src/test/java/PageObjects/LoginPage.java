package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Using page factory
public class LoginPage {

	
	WebDriver d;
	//find web elements of login page
	
	@FindBy(name="username")
	WebElement username;
	
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	WebElement InvalidMessage;
	
	public LoginPage(WebDriver driver)
	{
		d=driver;
		PageFactory.initElements(d, this);
	}
	
	//Methods for operations
	public void setUserName(String name)
	{
		username.sendKeys(name);
	}
	
	public void SetPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void clickLoginButton()
	{
		loginBtn.submit();
	}
	
	public String getInvalidText()
	{
		return (InvalidMessage.getText());
	
	}
}
