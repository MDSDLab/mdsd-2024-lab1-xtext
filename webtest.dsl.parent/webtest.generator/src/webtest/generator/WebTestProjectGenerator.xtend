package webtest.generator

class WebTestProjectGenerator {
	def generateHelloWorld() {
		'''
		webtest example.HelloWorldTest
		
		test SayHelloTest
			print "Hello World!"
		end
		'''
	}
	
	def generatePage() {
		'''
		package webtest.selenium.api;
		
		import org.openqa.selenium.NoSuchShadowRootException;
		import org.openqa.selenium.SearchContext;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		
		public class Page {
			private WebDriver _driver;
			private SearchContext _context;
		
			public Page(WebDriver driver, SearchContext context) {
				_driver = driver;
				_context = context;
				if (context instanceof WebElement) {
					var webElement = (WebElement)context;
					try {
						var shadowRoot = webElement.getShadowRoot();
						if (shadowRoot != null) _context = shadowRoot;
					} catch (NoSuchShadowRootException ex) {
						// ignore
					}
				}
			}
			
			public WebDriver getDriver() {
				return _driver;
			}
			
			public SearchContext getContext() {
				return _context;
			}
		
			protected void sleep(int seconds) {
		
				try {
					Thread.sleep(seconds*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		'''
	}

	def generatePageElement() {
		'''
		package webtest.selenium.api;
		
		import java.util.ArrayList;
		import java.util.List;
		
		import org.openqa.selenium.By;
		import org.openqa.selenium.ElementNotInteractableException;
		import org.openqa.selenium.InvalidArgumentException;
		import org.openqa.selenium.InvalidSelectorException;
		import org.openqa.selenium.JavascriptException;
		import org.openqa.selenium.JavascriptExecutor;
		import org.openqa.selenium.SearchContext;
		import org.openqa.selenium.WebElement;
		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		
		public class PageElement {
			private static Logger logger = LoggerFactory.getLogger(PageElement.class);
		
			private Page _page;
			private JavascriptExecutor _javascript;
			private SearchContext _context;
			private String _tag;
			private String _label;
			private WebElement _element;
			private List<By> _bys;
			
			public PageElement(Page page, String tag, String label) {
				this._page = page;
				this._javascript = (JavascriptExecutor)page.getDriver();
				this._context = page.getContext();
				this._tag = tag;
				this._label = label;
			}
		
			public PageElement(Page page, WebElement element) {
				this._page = page;
				this._javascript = (JavascriptExecutor)page.getDriver();
				this._context = page.getContext();
				this._element = element;
			}
		
			public boolean exists() {
				return find() != null && _element.isDisplayed();
			}
			
			public boolean contains(String text) {
				var elementText = getText();
				if (elementText == null) return false;
				return elementText.contains(text);
			}
			
			public WebElement find() {
				if (_element == null) {
					_element = findSingleElement();
					if (_element != null) logger.info("Page element {} '{}' exists.", _tag, _label);
					else logger.info("Page element {} '{}' does not exist.", _tag, _label);
				}
				return _element;
			}
			
			public List<WebElement> findAll() {
				return findAllElements();
			}
			
			public List<PageElement> forEach() {
				var result = new ArrayList<PageElement>();
				for (var element: findAll()) {
					result.add(new PageElement(_page, element));
				}
				return result;
			}
			
			public void clear() {
				find();
				if (_element == null) return; 
				_element.clear();
			}
			
			public void focus() {
				find();
				if (_element == null) return;
				_javascript.executeScript("arguments[0].focus();", _element);
			}
			
			public void scrollIntoView() {
				find();
				if (_element == null) return;
				_javascript.executeScript("arguments[0].scrollIntoView(true);", _element);
			}
			
			public void click() {
				find();
				if (_element == null) return;
				try {
					_element.click();
				} catch (ElementNotInteractableException ex) {
					_javascript.executeScript("arguments[0].click();", _element);
				}
			}
			
			public void sendKeys(CharSequence... keysToSend) {
				find();
				if (_element == null) return;
				_element.sendKeys(keysToSend);
			}
			
			public void fill(String value) {
				clear();
				click();
				sendKeys(value);
			}
			
			public void highlight(boolean enable) {
				find();
				if (_element == null) return;
				if (enable) _javascript.executeScript("arguments[0].style.outline = 'red solid 4px'; arguments[0].style.outlineOffset = '-4px'; arguments[0].scrollIntoView({ block: 'center', inline: 'center' });", _element);
				else _javascript.executeScript("arguments[0].style.outline = ''; arguments[0].style.outlineOffset = '';", _element);
			}
			
			public boolean isSelected() {
				find();
				if (_element == null) return false; 
				return _element.isSelected();
			}
			
			public boolean isEnabled() {
				find();
				if (_element == null) return false; 
				return _element.isEnabled();
			}
			
			public boolean isDisplayed() {
				find();
				if (_element == null) return false; 
				return _element.isDisplayed();
			}
			
			public String getText() {
				find();
				if (_element == null) return null;
				if ("input".equals(_element.getTagName())) {
					return _element.getAttribute("value");
				} else {
					return _element.getText();
				}
			}
			
			@Override
			public String toString() {
				return getText();
			}
			
			private List<By> getBys() {
				if (_bys == null) {
					_bys = new ArrayList<By>();
					_bys.add(By.xpath(".//"+_tag+"[@id='"+_label+"']"));
					_bys.add(By.xpath(".//"+_tag+"[@name='"+_label+"']"));
					_bys.add(By.xpath(".//"+_tag+"[@aria-label='"+_label+"']"));
					_bys.add(By.xpath(".//"+_tag+"[@aria-labelledby='"+_label+"']"));
					_bys.add(By.xpath(".//"+_tag+"[@label='"+_label+"']"));
					_bys.add(By.xpath(".//"+_tag+"[@for='"+_label+"']"));
					_bys.add(By.cssSelector("#"+_label+" "+_tag));
					_bys.add(By.xpath(".//"+_tag+"[@placeholder='"+_label+"']"));
					_bys.add(By.xpath(".//"+_tag+"[@value='"+_label+"']"));
					_bys.add(By.xpath(".//"+_tag+"[contains(text(),'"+_label+"')]"));
					_bys.add(By.xpath(".//"+_tag+"[contains(@class,'"+_label+"')]"));
				}
				return _bys;
			}
			
			
			private WebElement findSingleElement() {
				var bys = getBys();
				for (var by: bys) {
					var single = findSingleElement(_context, by, false);
					if (single != null) return single;
				}
				for (var by: bys) {
					var single = findSingleElement(_context, by, true);
					if (single != null) return single;
				}
				return null;
			}
			
			private List<WebElement> findAllElements() {
				var bys = getBys();
				for (var by: bys) {
					var single = findAllElements(_context, by);
					if (single != null && single.size() > 0) return single;
				}
				return new ArrayList<WebElement>();
			}
			
			private WebElement findSingleElement(SearchContext context, By by, boolean findFirst) {
				try {
					var results = context.findElements(by);
					if (results.size() == 0) {
						return null;
					} else if (results.size() == 1 || findFirst) {
						return results.get(0);
					} else {
						return null;
					}
				} catch (InvalidSelectorException ex) {
					return null;
				} catch (InvalidArgumentException ex) {
					return null;
				} catch (JavascriptException jsex) {
					return null;
				}
			}
			
			private List<WebElement> findAllElements(SearchContext context, By by) {
				try {
					var results = context.findElements(by);
					return results;
				} catch (InvalidSelectorException ex) {
					return null;
				} catch (JavascriptException jsex) {
					return null;
				}
			}
		}
		'''
	}

	def generateSeleniumTest() {
		'''
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
				driver = new ChromeDriver();
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
		'''
	}
	
	def generateLogback() {
		'''
		<?xml version="1.0" encoding="UTF-8" ?>
		<configuration>
		  <import class="ch.qos.logback.classic.encoder.PatternLayoutEncoder"/>
		  <import class="ch.qos.logback.core.ConsoleAppender"/>
		
		  <appender name="STDOUT" class="ConsoleAppender">
		    <encoder class="PatternLayoutEncoder">
		      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} -%kvp- %msg%n</pattern>
		    </encoder>
		  </appender>
		
		  <root level="info">
		    <appender-ref ref="STDOUT"/>
		  </root>
		</configuration>
		'''
	}
	
	def generateLogbackTest() {
		'''
		<?xml version="1.0" encoding="UTF-8" ?>
		<configuration>
		  <import class="ch.qos.logback.classic.encoder.PatternLayoutEncoder"/>
		  <import class="ch.qos.logback.core.ConsoleAppender"/>
		
		  <appender name="STDOUT" class="ConsoleAppender">
		    <encoder class="PatternLayoutEncoder">
		      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} -%kvp- %msg%n</pattern>
		    </encoder>
		  </appender>
		
		  <root level="info">
		    <appender-ref ref="STDOUT"/>
		  </root>
		</configuration>
		'''
	}

	
	def generatePomXml(String projectName) {
		'''
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		  <modelVersion>4.0.0</modelVersion>
		  <groupId>webtest</groupId>
		  <artifactId>«projectName»</artifactId>
		  <version>0.0.1-SNAPSHOT</version>
		
		  <properties>
		    <version.java>21</version.java>
		    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		    <version.maven.compiler>3.11.0</version.maven.compiler>
		    <version.maven.surefire>3.1.2</version.maven.surefire>
		    <version.selenium>4.22.0</version.selenium>
		    <version.junit>5.10.2</version.junit>
		    <version.slf4j>2.0.13</version.slf4j>
		    <version.logback>1.5.6</version.logback>
		  </properties>
		  
		  <dependencies>
			<dependency>
			    <groupId>org.slf4j</groupId>
			    <artifactId>slf4j-api</artifactId>
			    <version>${version.slf4j}</version>
			</dependency>
			<dependency>
			    <groupId>ch.qos.logback</groupId>
			    <artifactId>logback-classic</artifactId>
			    <version>${version.logback}</version>
			</dependency>
		    <dependency>
		      <groupId>org.seleniumhq.selenium</groupId>
		      <artifactId>selenium-chrome-driver</artifactId>
		      <version>${version.selenium}</version>
		    </dependency>
		    <dependency>
		      <groupId>org.seleniumhq.selenium</groupId>
		      <artifactId>selenium-java</artifactId>
		      <version>${version.selenium}</version>
		    </dependency>
		    <dependency>
		      <groupId>org.junit.jupiter</groupId>
		      <artifactId>junit-jupiter</artifactId>
		      <version>${version.junit}</version>
		      <scope>test</scope>
		    </dependency>
		  </dependencies>
		  
		  <build>
		    <finalName>${project.artifactId}</finalName>
		    <plugins>
		      <plugin>
		        <groupId>org.apache.maven.plugins</groupId>
		        <artifactId>maven-compiler-plugin</artifactId>
		        <version>${version.maven.compiler}</version>
		        <configuration>
		            <release>${version.java}</release>
		        </configuration>
		      </plugin>
		      <plugin>
		        <artifactId>maven-surefire-plugin</artifactId>
		        <version>${version.maven.surefire}</version>
		        <configuration>
		          <systemPropertyVariables>
		            <webdriver.chrome.driver>c:/Programs/chromedriver/chromedriver.exe</webdriver.chrome.driver>
		          </systemPropertyVariables>
		        </configuration>
		      </plugin>
		    </plugins>
		  </build>
		  
		</project>
		'''
	}

	def generateClasspath() {
		'''
		<?xml version="1.0" encoding="UTF-8"?>
		<classpath>
			<classpathentry kind="src" output="target/classes" path="src/main/java">
				<attributes>
					<attribute name="optional" value="true"/>
					<attribute name="maven.pomderived" value="true"/>
				</attributes>
			</classpathentry>
			<classpathentry kind="src" output="target/test-classes" path="src-gen/test/java">
				<attributes>
					<attribute name="test" value="true"/>
					<attribute name="optional" value="true"/>
					<attribute name="maven.pomderived" value="true"/>
				</attributes>
		  </classpathentry>
			<classpathentry excluding="**" kind="src" output="target/classes" path="src/main/resources">
				<attributes>
					<attribute name="maven.pomderived" value="true"/>
					<attribute name="optional" value="true"/>
				</attributes>
			</classpathentry>
			<classpathentry kind="src" output="target/test-classes" path="src/test/java">
				<attributes>
					<attribute name="test" value="true"/>
					<attribute name="optional" value="true"/>
					<attribute name="maven.pomderived" value="true"/>
				</attributes>
			</classpathentry>
			<classpathentry excluding="**" kind="src" output="target/test-classes" path="src/test/resources">
				<attributes>
					<attribute name="test" value="true"/>
					<attribute name="maven.pomderived" value="true"/>
					<attribute name="optional" value="true"/>
				</attributes>
			</classpathentry>
			<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-21">
				<attributes>
					<attribute name="module" value="true"/>
					<attribute name="maven.pomderived" value="true"/>
				</attributes>
			</classpathentry>
			<classpathentry kind="con" path="org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER">
				<attributes>
					<attribute name="maven.pomderived" value="true"/>
				</attributes>
			</classpathentry>
			<classpathentry kind="output" path="target/classes"/>
		</classpath>
		'''
	}

	def generateGitignore() {
		'''
		target
		output
		'''
	}
	

}