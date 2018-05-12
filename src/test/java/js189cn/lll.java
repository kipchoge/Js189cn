package js189cn;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;





public class lll {

	public  WebDriver driver;
	
	public static void main(String[] args) throws Exception {
		lll autoLogin = new lll();
		autoLogin.initDriver();
		autoLogin.login();
		autoLogin.releaseMateriel();
		autoLogin.releaseGoods();
	}

	public void initDriver() {
		System.setProperty("webdriver.chrome.driver","D:\\eclipse-workspace\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://go189.cn/emall/hdscOP/jsp/openter.jsp");
	}
	
	public void login() throws InterruptedException, IOException {
		do {
		driver.findElement(By.id("loginName")).clear();
		driver.findElement(By.id("loginName")).sendKeys("mafei");
		driver.findElement(By.id("loginPwd")).clear();
		driver.findElement(By.id("loginPwd")).sendKeys("xwtec@JSDX2016");
		driver.findElement(By.id("_checkCodeImg")).click();
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.id("_checkCodeImg"));
		new TesseractOCR().getVerifyCodeJPG(driver,ele);
		String verifyCode = new TesseractOCR().recognizeText("D:\\Test\\Tesseract-OCR\\test.jpg");
		System.out.println(verifyCode);
		driver.findElement(By.id("checkCode")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("checkCode")).sendKeys(verifyCode);
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@class='login-action clearfix']/input")).click();
		Thread.sleep(4000);
		}while(driver.getCurrentUrl().equals("http://go189.cn/emall/hdscOP/jsp/openter.jsp"));
	}
	
	public void releaseMateriel() throws InterruptedException {
		driver.findElement(By.xpath("//ul[@id='drop-menu']/li[4]/span[1]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//ul[@id='drop-menu']/li[4]/ul/li[1]/span")).click();
		Thread.sleep(2000);//û�����SLEEP���Ҳ���iframe�����Ԫ�أ�����
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='info']/div[2]/div[2]/iframe")));
		WebElement classify = driver.findElement(By.id("fmaterialgroupid"));		
		Select downlist1 = new Select(classify);
		downlist1.selectByIndex(2);
		driver.findElement(By.xpath("//form[@id='form']/div[2]/input[1]")).click();
		Thread.sleep(500);
		//��д������Ϣ
		driver.findElement(By.id("fname")).sendKeys("��������");
		driver.findElement(By.id("fshortname")).sendKeys("����");
		driver.findElement(By.id("fdescription")).sendKeys("����ר�ã�����ѡ��");
		WebElement color = driver.findElement(By.id("fidones"));
		Select downlist2 = new Select(color);
		int a = downlist2.getOptions().size();
		Random rand1 = new Random();
		int i = rand1.nextInt(a - 1) + 1;// Ϊ�˲�ѡ����һ���һ���ǡ���ѡ��
		downlist2.selectByIndex(i);
		driver.findElement(By.xpath("//input[@value='ȷ��']")).click();
		Alert alt = driver.switchTo().alert();
		alt.accept();
		driver.switchTo().defaultContent(); //�˳�iframe���ص�������
		Thread.sleep(500);
	}

	public void releaseGoods() throws Exception {
		driver.findElement(By.xpath("//ul[@id='drop-menu']/li[4]/ul/li[2]/span")).click();
		Thread.sleep(500);
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='info']/div[2]/div[3]/iframe")));
		driver.findElement(By.id("cfname")).sendKeys("����0511");
		driver.findElement(By.id("cfprice")).sendKeys("1");
		driver.findElement(By.id("cfpriceend")).sendKeys("2");
		driver.findElement(By.id("cfsalepoint")).sendKeys("������Ʒ�������򣡣���");
		String currentWindow = driver.getWindowHandle();// ��ȡ��ǰ���ھ��
		driver.findElement(By.id("addMater1")).click();
		this.checkNewPage();
		driver.findElement(By.id("materialName")).sendKeys("����");
		Thread.sleep(200);
		driver.findElement(By.xpath("//form[@id='mainForm']//td[2]/input[1]")).click();//�������
		Thread.sleep(200);
		driver.findElement(By.xpath("//table[@id='tableList']//tr[2]/td[1]")).click();//���ѡ��
		driver.findElement(By.xpath("//div[@class='content01']/input")).sendKeys(Keys.SPACE);//ȷ����ѡ
		Thread.sleep(200);
		driver.switchTo().window(currentWindow);// �ص�ԭ��ҳ��
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='info']/div[2]/div[3]/iframe")));//����iframe
		driver.findElement(By.xpath("//select[@id='leftselect1']/option")).click();//ȷ����ѡ
		driver.findElement(By.xpath("//div[@id='materSelect1']/input[2]")).click();//��ӵ��Ҳ����
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@id='materSelectSure1']/input[2]")).click();//ȷ����ѡ
		Thread.sleep(200);
		driver.findElement(By.xpath("//table[@id='matettablelist1']//tr[2]/td[9]/a[2]")).click();//�༭
		this.checkNewPage();
		driver.findElement(By.id("CFCanSaleQty")).sendKeys("100");
		driver.findElement(By.id("cfprice")).sendKeys("0.01");
		driver.findElement(By.xpath("//form[@id='form']/div/input[2]")).click();
		driver.switchTo().window(currentWindow);// �ص�ԭ��ҳ��
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='info']/div[2]/div[3]/iframe")));//����iframe
		Thread.sleep(200);
		driver.findElement(By.xpath("//form[@id='form']//input[@value='��һ��']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='subCategoryId0']/ul[2]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@id='subCategoryId1']/ul[5]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@id='subCategoryId2']/ul[1]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//form[@id='form']/div/div/input[3]")).click();
		//�Ѿ���������ҳ��......
		driver.findElement(By.xpath("//ul[@id='mainul']/li[10]/input")).clear();
		driver.findElement(By.xpath("//ul[@id='mainul']/li[10]/input")).sendKeys("100");
		WebElement channel = driver.findElement(By.xpath("//select[@id='channelId']"));
		Select downlist3 = new Select(channel);
		downlist3.selectByIndex(2);
		
	}
	
	public void checkNewPage() throws Exception {
		String currentWindow = driver.getWindowHandle();// ��ȡ��ǰ���ھ��
		Set<String> handles = driver.getWindowHandles();// ��ȡ���д��ھ��
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			if (currentWindow == it.next()) {
				continue;
			}
			driver.switchTo().window(it.next());// �л����´���
			// driver.switchTo().window(currentWindow);// �ص�ԭ��ҳ��
		}
	}
	
}
