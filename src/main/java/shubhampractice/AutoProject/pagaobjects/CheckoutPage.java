package shubhampractice.AutoProject.pagaobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhampractice.AutoProject.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	public WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//driver.findElement(By.xpath("//input[@placeholder='Select Country']"))	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
//	driver.findElement(By.xpath("//span[text()=' India']"))	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement submit;
//	driver.findElement(By.xpath("//span[text()=' India']"))	
	@FindBy(xpath="//span[text()=' India']")
	WebElement selectCountry;
	
	By results=By.cssSelector(".ta-results");
	By c=By.xpath("//a[normalize-space()='Place Order']");
	
//	Actions a=new Actions(driver);
//	a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
//	
//	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
//	driver.findElement(By.xpath("//span[text()=' India']")).click();
	public void selectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country, "india").build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() throws InterruptedException
	{
		waitForElementToAppear(c);

		Actions a=new Actions(driver);
		Thread.sleep(2000);
    	a.moveToElement(submit, 10, 5).click().build().perform();
		submit.click();


		return new ConfirmationPage(driver);
		
	}

}
