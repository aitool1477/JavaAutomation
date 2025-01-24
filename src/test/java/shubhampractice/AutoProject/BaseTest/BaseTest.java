package shubhampractice.AutoProject.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import shubhampractice.AutoProject.pagaobjects.LandingPage;

public class BaseTest {

	
//	WebDriverManager.chromedriver().setup();
//	WebDriver driver=new ChromeDriver();
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	driver.manage().window().maximize();	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resorces\\GlobalData.properties");
		//FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\AutoProject\\src\\main\\java\\Resorces\\GlobalData.properties");

		prop.load(fis);
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if (browserName.contains("edge"))
		{
			EdgeOptions options=new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver=new EdgeDriver(options);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
				
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{	//reading JSON to string
		String jsonContent=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//String to JacksonDataBind
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		//data is list with 2 argument
		
		return data;
		
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"reports"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"reports"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		
		return landingPage;
	}
//	LandingPage landingPage=new LandingPage(driver);
//	landingPage.goTo();
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
}
