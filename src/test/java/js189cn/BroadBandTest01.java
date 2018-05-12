package js189cn;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class BroadBandTest01 {
	
	public static ChromeDriver driver;

	public void checkNewPage() throws Exception {  
		String currentWindow = driver.getWindowHandle();//��ȡ��ǰ���ھ��  
		Set<String> handles = driver.getWindowHandles();//��ȡ���д��ھ��  
		Iterator<String> it = handles.iterator();  
		while (it.hasNext()) {  
		if (currentWindow == it.next()) {  
		continue;  
		}  
		WebDriver window = driver.switchTo().window(it.next());//�л����´���  
		System.out.println(window.getTitle() +" "+ window.getCurrentUrl());  
		window.close();//�ر��´���  
		}  
		driver.switchTo().window(currentWindow);//�ص�ԭ��ҳ��  
	}  
	
	public void InitDriver(){
		System.setProperty("webdriver.chrome.driver","D:\\selenium\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://js.189.cn/umall/broadband/index");
	}
	
	public void printGoodsList() throws Exception{
		System.out.println(driver.findElementById("cityName").getText()+"������Ʒ��Ϣ");
		System.out.println("�鿴�Żݿ��------------------------------------------");
		 List<WebElement> goods1 = driver.findElements(By.linkText("��������"));
		for(WebElement good:goods1) {
			 good.click();
			 this.checkNewPage();
		 }
		System.out.println("��������ʲ鿴------------------------------------------");
		driver.findElement(By.linkText("��������ʲ鿴")).click();
		List<WebElement> goods2 = driver.findElements(By.linkText("��������"));
		for(WebElement good:goods2) {
			 good.click();
			 this.checkNewPage();
		}
	}
	
	 public static void main(String[] args) throws Exception{
		 BroadBandTest01 broad = new BroadBandTest01();
		 broad.InitDriver();
		 Thread.sleep(2000);
		 for(int i=0;i<13;i++){
			 try {
				 WebElement city = driver.findElement(By.id("selectCityName"));
				 Select downList = new Select(city);
				 downList.selectByIndex(i);
				 Thread.sleep(5000);
				 broad.printGoodsList();
		        }
		        catch(org.openqa.selenium.StaleElementReferenceException ex)
		        {
		        	WebElement city = driver.findElement(By.id("selectCityName"));
		        	Select downList = new Select(city);
		        	downList.selectByIndex(i);
		        	Thread.sleep(5000);
		        	broad.printGoodsList();
		        }
		 }
	 }
}
