package js189cn;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BroadBandTest {
	
	public ChromeDriver driver;

	public void InitDriver(){
		System.setProperty("webdriver.chrome.driver","D:\\selenium\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver();
		driver.get("http://js.189.cn/umall/broadband/index");
		driver.manage().window().maximize();
	}
	
	
	 public static void main(String[] args){
		 BroadBandTest broad = new BroadBandTest();
		 broad.InitDriver();
		}

}
