package js189cn;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class AutoLogin189 {

	private static  ChromeDriver driver;

	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver","D:\\eclipse-workspace\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		//打开网厅登录页面并选择手机登录
		driver.get("http://js.189.cn/nservice/login/toLogin?favurl=http://js.189.cn/index");
		driver.findElementByXPath("//ul[@id='menu1']/li[1]").click();
			
		do{
		driver.findElementByXPath("//input[@id='cellphone']").clear();
		driver.findElementByXPath("//input[@id='cellphone']").sendKeys("17312813155");
		driver.findElementByXPath("//div[@class='login_con_line select']/input").sendKeys("371516");
		WebElement ele = driver.findElementByXPath("//p[@class='login_con_line']/span");
	
		new TesseractOCR().getVerifyCodeJPG(driver,ele);
		String verifyCode = new TesseractOCR().recognizeText("D:\\Test\\Tesseract-OCR\\test.jpg");
		System.out.println(verifyCode);
		Thread.sleep(100);
		driver.findElementByXPath("//input[@name='validateCodeNumber']").sendKeys(verifyCode);
		Thread.sleep(100);
		driver.findElement(By.id("login_byPhone")).click();
		Thread.sleep(1000);
		}while(driver.getCurrentUrl().equals("http://js.189.cn/nservice/login/toLogin?menuType=0&logonPattern=1&favurl=http%3A%2F%2Fjs.189.cn%2Findex"));
		
	}

}
