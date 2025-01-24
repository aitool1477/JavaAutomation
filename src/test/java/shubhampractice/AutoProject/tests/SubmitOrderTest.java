package shubhampractice.AutoProject.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shubhampractice.AutoProject.BaseTest.BaseTest;
import shubhampractice.AutoProject.pagaobjects.CartPage;
import shubhampractice.AutoProject.pagaobjects.CheckoutPage;
import shubhampractice.AutoProject.pagaobjects.ConfirmationPage;
import shubhampractice.AutoProject.pagaobjects.Orderpage;
import shubhampractice.AutoProject.pagaobjects.ProductCataloge;

public class SubmitOrderTest extends BaseTest {
	
	
	
	String productName = "BANARSI SAREE";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {
		
		// LandingPage landingPage=launchApplication();

		ProductCataloge productCataloge = landingPage.loginApplication(email, password);
		// ProductCataloge productCataloge=new ProductCataloge(driver);

		List<WebElement> products = productCataloge.getProductList();
		productCataloge.addProductToCart(productName);
		CartPage cartPage = productCataloge.goToCart();

		boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
//		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
//
//		String confirmMessage = confirmationPage.getConfirmationMessage();
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
////		System.out.println("Test Done");

	}

	// To Verify IPHONE 13 PRO is displaying in order page

	@Test(dependsOnMethods = { "submitOrder" }, groups="smoke")
	public void OrderHistoryTest() {
		ProductCataloge productCataloge = landingPage.loginApplication("pravinsolanke2610@gmail.com", "Pravin@123");
		Orderpage orderpage = productCataloge.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		
		return new Object[] [] { {"pravinsolanke2610@gmail.com", "Pravin@123", "IPHONE 13 PRO"}, {"shetty@gmail.com", "Iamking@000", "BANARSI SAREE"} };
		
	}

}
