package shubhampractice.AutoProject.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class JsonSubmitOrderTest extends BaseTest {
	
	
	
	String productName = "BANARSI SAREE";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String>input) throws IOException, InterruptedException {
		ProductCataloge productCataloge = landingPage.loginApplication(input.get("email"),input.get("password"));
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
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
		
//		return new Object[] [] { {"pravinsolanke2610@gmail.com", "Pravin@123", "IPHONE 13 PRO"}, {"shetty@gmail.com", "Iamking@000", "BANARSI SAREE"} };

//		HashMap<String, String> map = new HashMap<String,String>();
//		map.put("email", "shetty@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("product", "BANARSI SAREE ");
//		
//		
//		HashMap<String, String> map1 = new HashMap<String,String>();
//		map1.put("email", "pravinsolanke2610@gmail.com");
//		map1.put("password", "Pravin@123");
//		map1.put("product", "IPHONE 13 PRO");
		
		
	}
	

}
