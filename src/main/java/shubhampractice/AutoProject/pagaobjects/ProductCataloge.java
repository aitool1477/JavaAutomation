package shubhampractice.AutoProject.pagaobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shubhampractice.AutoProject.AbstractComponent.AbstractComponent;

public class ProductCataloge extends AbstractComponent {

	WebDriver driver;
	
	public ProductCataloge(WebDriver driver)
	{
		super(driver);
		//Initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	//PageFactory 

	@FindBy(css="div.mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.cssSelector("div.mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");

//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));		
//	List<WebElement> products= driver.findElements(By.cssSelector("div.mb-3"));	
	public List<WebElement> getProductList()
	{	
		
		waitForElementToAppear(productsBy);
		return products;
	}
//	WebElement prod =products.stream().filter(product->product.findElement(By.cssSelector("h5 b")).getText().equals(productName)).findFirst().orElse(null);
	
	public WebElement getProductByName(String productName)
	{
		
		WebElement prod= getProductList().stream().filter(s->s.findElement(By.cssSelector("h5 b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

//	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	public void addProductToCart(String productName) throws InterruptedException
	{

		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementDisappear(spinner);

	}
	
	

}
