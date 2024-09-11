package webtest.selenium.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumTest {
	private static final String DEFAULT_CHROME_DRIVER_LOCATION = "c:/Programs/chromedriver/chromedriver.exe";
	public static final String OUTPUT_PATH = "output";
	
	private static Logger logger = LoggerFactory.getLogger(SeleniumTest.class);
	private static int screenshotIndex = 0;
	protected static  WebDriver driver;
	protected static Actions actions;
	protected static JavascriptExecutor javascript;
	protected static FluentWait<WebDriver> wait;
	protected static TakesScreenshot screenshot;
	protected PrintWriter _writer;
	
	@BeforeAll
	public static void startup() throws IOException {
		var chromeDriverLocation = System.getProperty("webdriver.chrome.driver");
		if (chromeDriverLocation == null) {
			chromeDriverLocation = DEFAULT_CHROME_DRIVER_LOCATION;
			System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
		}
		logger.info("Using chromedriver: "+chromeDriverLocation);
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-search-engine-choice-screen");
		driver = new ChromeDriver(opt);
		javascript = (JavascriptExecutor)driver;
	    actions = new Actions(driver);
	    driver.manage().window().maximize();
		logger.info("Chrome is opened.");
		wait = new FluentWait<>(driver);
		wait.pollingEvery(Duration.ofSeconds(1));
		screenshot = (TakesScreenshot)driver;
		Files.createDirectories(Paths.get(OUTPUT_PATH));
	}
	
	@AfterAll
	public static void shutdown() {
    	driver.quit();
		logger.info("Chrome is closed.");
	}
	
	protected String capture() {
		return capture(null);
	}
	
	protected String capture(PageElement element) {
		if (element != null) element.highlight(true);
		try {
			sleep(1);
			var image = screenshot.getScreenshotAs(OutputType.BYTES);
			try {
				var fileName = "screenshot"+(++screenshotIndex)+".png";
				var filePath = OUTPUT_PATH+"/"+fileName;
				var stream = new FileOutputStream(filePath);
				try {
					stream.write(image);
					stream.flush();
					if (_writer != null) {
						_writer.print("<img src='");
						_writer.print(fileName);
						_writer.println("'/>");
					}
					logger.info("Captured: "+filePath);
					return filePath;
				} finally {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			if (element != null) element.highlight(false);
		}
		return null;
	}

	protected void sleep(int seconds) {

		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
