package shubhampractice.AutoProject.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shubhampractice.AutoProject.pagaobjects.CartPage;
import shubhampractice.AutoProject.pagaobjects.Orderpage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	public void waitForElementToAppear(By findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	public void waitForElementDisappear(WebElement ele) throws InterruptedException 
	{
		//due to application issue it is waiting so using thread
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(3000);
	}
	
//	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	WebElement cartHeader;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[2]")
	WebElement orderHeader;
	
	public CartPage goToCart()
	{
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;


	}
//	driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
	
	public void waitForWebElementToAppear(WebElement findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public Orderpage goToOrderPage()
	{
		orderHeader.click();
		Orderpage orderPage=new Orderpage(driver);
		return orderPage;
	}
	
	

}
