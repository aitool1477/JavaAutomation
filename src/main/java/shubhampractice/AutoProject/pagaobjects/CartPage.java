package shubhampractice.AutoProject.pagaobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhampractice.AutoProject.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{	

	WebDriver driver;
//	driver.findElement(By.xpath("//button[text()='Checkout']")).click();
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;

	//	List <WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	
//	Boolean match= cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match =cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
//	driver.findElement(By.xpath("//button[text()='Checkout']")).click();
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
		
}
