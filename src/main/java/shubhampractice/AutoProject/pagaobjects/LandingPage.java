package shubhampractice.AutoProject.pagaobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhampractice.AutoProject.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		//Initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	
	//PageFactory 
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']\r\n")
	WebElement errorMessage;
	//Action Methods
	public ProductCataloge loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCataloge productCataloge=new ProductCataloge(driver);
		return productCataloge;

	}
//	driver.get("https://rahulshettyacademy.com/client/");
	
	
//	driver.findElement(By.id("userEmail")).sendKeys("pravinsolanke2610@gmail.com");
//	driver.findElement(By.id("userPassword")).sendKeys("Pravin@123");
//	driver.findElement(By.id("login")).click();	
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		 
	}
	
	
	
	
	

}
