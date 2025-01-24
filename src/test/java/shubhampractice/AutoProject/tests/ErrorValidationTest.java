package shubhampractice.AutoProject.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import shubhampractice.AutoProject.BaseTest.BaseTest;
import shubhampractice.AutoProject.BaseTest.Retry;
import shubhampractice.AutoProject.pagaobjects.CartPage;
import shubhampractice.AutoProject.pagaobjects.ProductCataloge;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException
	{
		
		
		//LandingPage landingPage=launchApplication();	

		ProductCataloge productCataloge =landingPage.loginApplication("anshika@gmail.com", "Iamk");
		//ProductCataloge productCataloge=landingPage.loginApplication("anshika@gmail.com", "Iamk");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	String productName="IPHONE 13 PRO";
	//LandingPage landingPage=launchApplication();	

	ProductCataloge productCataloge=landingPage.loginApplication("pravinsolanke2610@gmail.com", "Pravin@123");		
	//ProductCataloge productCataloge=new ProductCataloge(driver);
	
	List<WebElement>products=productCataloge.getProductList();
	productCataloge.addProductToCart(productName);
	CartPage cartPage=productCataloge.goToCart();
	
	boolean match=cartPage.VerifyProductDisplay("IPHONE 14 PRO");
	Assert.assertFalse(match);
	 
		
}

}
