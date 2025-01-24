package shubhampractice.AutoProject.pagaobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhampractice.AutoProject.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
		}

//	String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	public String getConfirmationMessage() throws InterruptedException
	{

		return confirmMessage.getText();
	}
	


	
}
	 