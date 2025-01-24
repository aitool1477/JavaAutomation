package shubhampractice.AutoProject.pagaobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import shubhampractice.AutoProject.AbstractComponent.AbstractComponent;

public class Orderpage extends AbstractComponent 
{
	WebDriver driver;

	public Orderpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
	List <WebElement> productNames;

	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match =productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
