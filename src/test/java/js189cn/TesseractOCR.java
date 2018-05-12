package js189cn;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TesseractOCR {

	public void getVerifyCodeJPG(WebDriver driver, WebElement ele) throws IOException {

		// Get entire page screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		Point point = ele.getLocation();

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);

		// Copy the element screenshot to disk
		File screenshotLocation = new File("D:\\Test\\Tesseract-OCR\\test.jpg");
		FileUtils.copyFile(screenshot, screenshotLocation);
	}

	public String recognizeText(String imageFile) {
		String result = ""; // �����ȡ����ʶ�����ݲ�����
		String tesseractExe = "D:\\Test\\Tesseract-OCR\\tesseract.exe";
		String output = "D:\\Test\\Tesseract-OCR\\result";
		// ����ѡ����װִ��������ַ�����ע�����֮����Ҫ�ӿո�ָ���
		String command = tesseractExe + " " + imageFile + " " + output;
		// System.out.println(command); // ������������ȷ�ϣ��ɹ�����д��� ��ɾ��
		try {
			// ʹ��Process����ȡִ������Ľ�����������������ж�
			Process process = Runtime.getRuntime().exec(command);
			int exeCode = process.waitFor();
			// ִ�еĽ���������Ϊ0����ʾ����ִ�гɹ�
			if (exeCode == 0) {
				// ��ȡ������ļ��е����ݣ������丳ֵ������result
				InputStream fis = new FileInputStream(output + ".txt");
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				result = br.readLine();
				br.close();
			} else {
				System.out.println("����ʶ���������δ����ִ��.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String Str = result.replaceAll(" ", "");
		return Str;
	}
}