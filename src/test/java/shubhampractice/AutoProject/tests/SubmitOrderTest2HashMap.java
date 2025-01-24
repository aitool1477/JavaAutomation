package shubhampractice.AutoProject.tests;

import java.io.IOException;
import java.util.HashMap;
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

public class SubmitOrderTest2HashMap extends BaseTest {
	
	
	
	String productName = "BANARSI SAREE";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String>input) throws IOException, InterruptedException {
		
		// LandingPage landingPage=launchApplication();

		ProductCataloge productCataloge = landingPage.loginApplication(input.get("email"),input.get("password"));
		// ProductCataloge productCataloge=new ProductCataloge(driver);

		List<WebElement> products = productCataloge.getProductList();
		productCataloge.addProductToCart(input.get("product"));
		CartPage cartPage = productCataloge.goToCart();

		boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
//		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
//
//		String confirmMessage = confirmationPage.getConfirmationMessage();
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		System.out.println("Test Done");

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
		
		//return new Object[] [] { {"pravinsolanke2610@gmail.com", "Pravin@123", "IPHONE 13 PRO"}, {"shetty@gmail.com", "Iamking@000", "BANARSI SAREE"} };
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("email", "shetty@gmail.com");
		map.put("password", "Iamking@000");
		map.put("product", "BANARSI SAREE ");
		
		
		HashMap<String, String> map1 = new HashMap<String,String>();
		map1.put("email", "pravinsolanke2610@gmail.com");
		map1.put("password", "Pravin@123");
		map1.put("product", "IPHONE 13 PRO");
		return new Object [][] {{map},{map1}};
		
		
	}

}
