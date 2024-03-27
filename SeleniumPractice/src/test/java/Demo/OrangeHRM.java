package Demo;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

//import junit.framework.Assert;

public class OrangeHRM {

	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver ; 

	@BeforeTest
	public void setup()
	{
		System.out.println("Before Test executed");
		// TODO Auto-generated method stub
		driver=new ChromeDriver();

		//maximise windows
		driver.manage().window().maximize();

		//open url
		driver.get(baseUrl);

		//timer i kept as 60 you can keep 40
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60)); //60 seconds
	}

	@Test(priority = 1, enabled=false)
	public void doLoginWithInvalidCredential() throws InterruptedException
	{
		//find username and enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		//find password and enter invalid password 
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1234");//wrong password

		//login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();


		String message_expected = "Invalid credentials";

		String message_actual = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();

		//Assert.assertTrue(message_actual.contains(message_expected));

		Assert.assertEquals(message_expected, message_actual);

		Thread.sleep(1500);
	}

	@Test(priority = 2, enabled=false)
	public void loginTestWithValidCredential()
	{
		//find username and enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		//find password and enter password admin123
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		//login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();


		// Verify if the login was successful by checking the page title
		String pageTitle = driver.getTitle();

		/*	if (pageTitle.equals("OrangeHRM")) {
			System.out.println("Login successful!");
		} else {
			System.out.println("Login failed!");
		}*/

		logOut();
		Assert.assertEquals("OrangeHRM", pageTitle);
	}

	@Test(priority =3, enabled=true)
	public void addEmployee() throws InterruptedException, IOException
	{
		logIn();
		//   //span[text()='PIM']
		//     //a[text()='Add Employee']

		///    //input[@placeholder='First Name']

		//     //input[@placeholder='Last Name']

		//    //button[normalize-space()='Save']

		//find PIM Menu and click on PIM Menu
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		//find Add employee and click on Add Employee option
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

		//enter first name
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Radha");

		//enter last name
		driver.findElement(By.xpath(" //input[@placeholder='Last Name']")).sendKeys("Gupta");


		//////////////////////Add Image////////////////////////////////////////////

		//add image
		driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")).click();


		Thread.sleep(5000);//pause of 5 seconds

		Runtime.getRuntime().exec("C://Users//ASUS//Desktop//CS_SeleniumExercises//SeleniumPractice//AddImageOrangeHRM.exe");


		Thread.sleep(5000);


		/////////////////////////////////////////////////////////////////////////////







		///Thread.sleep(2000);
		//click save button
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

		// Verify if the employee is successfully added by checking the employee list personal details
		String confirmationMessage = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();


		if (confirmationMessage.contains("Personal Details")) {
			System.out.println("Employee added successfully!");
		} else {
			System.out.println("Failed to add employee!");
		}

		logOut();
		Assert.assertEquals("Personal Details", confirmationMessage);

	}

	@Test(priority=4, enabled = false)
	public void searchEmployeeNyName() throws InterruptedException
	{
		logIn();

		//find PIM Menu and click on PIM Menu
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		//Select Employee List
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();

		driver.findElements(By.tagName("input")).get(1).sendKeys("Radha");

		//Click the search button.
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		//    //span[@class='oxd-text oxd-text--span']
		Thread.sleep(5000)	;
		List<WebElement> element=	driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));

		String expected_message = "Record Found";
		String message_actual = element.get(0).getText();
		System.out.println(message_actual);

		logOut();

		Assert.assertTrue(message_actual.contains(expected_message));



		/*for (int i = 0 ; i<element.size(); i++)
	{
		 System.out.println("At index "+ i + "text is :" + element.get(i).getText());  
	}*/

		//	•	Verify that the record is found.
		//	•	Logout the user.

	}

	@Test(priority =5, enabled=false)
	public void searchEmployeeById() throws InterruptedException
	{

		String empId = "0372";
		String message_actual ="";
		logIn();


		//find PIM Menu and click on PIM Menu
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		//Select Employee List
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();

		//enter empoyee id
		driver.findElements(By.tagName("input")).get(2).sendKeys(empId);

		//Click the search button.
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		Thread.sleep(2000)	;

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0," + 500 + ")");

		Thread.sleep(2000)	;


		List<WebElement> rows = driver.findElements(By.xpath("(//div[@role='row'])"));


		if(rows.size()>1)
		{
			message_actual = driver.findElement(By.xpath("((//div[@role='row'])[2]/div[@role='cell'])[2]")).getText();

		}

		logOut();
		Assert.assertEquals(empId, message_actual);

	}

	@Test(priority=6, enabled = false)	
	public void fileUpload() throws IOException, InterruptedException
	{
		logIn();	

		//find PIM Menu and click on PIM Menu
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		//click on configuration button
		driver.findElement(By.xpath("//span[@class='oxd-topbar-body-nav-tab-item']")).click();


		//click on Data import
		driver.findElement(By.partialLinkText("Data ")).click();

		//click on browse button
		driver.findElement(By.xpath("//div[@class='oxd-file-button']")).click();


		Thread.sleep(5000);//pause of 5 seconds

		Runtime.getRuntime().exec("C://Users//ASUS//Desktop//CS_SeleniumExercises//SeleniumPractice//FileUploadOrangeHRM.exe");

		Thread.sleep(5000);

		//click on upload button
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		logOut();


	}

	public void logIn()
	{
		//find username and enter username "Admin"
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		//find password and enter password admin123
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		//login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

	}

	public void logOut() 
	{
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		//driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

		List <WebElement> elementlist = driver.findElements(By.xpath("//a[@class='oxd-userdropdown-link']"));

		/*for (int i=0; i<elementlist.size(); i++)
		{
			Thread.sleep(1000);
			System.out.println(i + ":" + elementlist.get(i).getText());

		}*/

		elementlist.get(3).click();//click on logout



	}

	@Test(priority=7, enabled=true)
	public void deleteEmployee() throws InterruptedException
	{
		logIn();

		//find PIM Menu and click on PIM Menu
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		//Select Employee List
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();

		//enter employee name
		driver.findElements(By.tagName("input")).get(1).sendKeys("Odis");

		//driver.findElement(By.tagName("input")).get(1).sendKeys("Nesta");


		//Click the search button.
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();


		Thread.sleep(3000);
		///////////////////Delete/////////////////////////

		//click on delete icon of the record
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();
		
		
		//click on yes, delete messaage button
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();

		//check for message "No Record Found"
		String msg = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]")).getText();

		Assert.assertEquals(msg, "No Records Found");

		Thread.sleep(5000);
		logOut();

	}


	@AfterTest
	public void tearDown() throws InterruptedException
	{

		//	logOut();

		Thread.sleep(5000);//wait for 5 secs before quit
		driver.close();
		driver.quit();

	}

	//   https://github.com/PallaviTanpure/OrangeHRM_SeleniumPythonProject/blob/master/pageObjects/EmployeePage.py

}
