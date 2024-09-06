package example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import webtest.selenium.api.Page;
import webtest.selenium.api.PageElement;
import webtest.selenium.api.SeleniumTest;

public class ManualTest extends SeleniumTest {
	private static Logger logger = LoggerFactory.getLogger(ManualTest.class);

	
	@Test
	public void LetMeGoogleThatForYou() {
		try {
			_writer = new PrintWriter(OUTPUT_PATH+"/"+"LetMeGoogleThatForYou.html");
			try {
				var _pageStack = new ArrayList<Page>();
				var _page = new Page(driver, driver);
				_writer.print("<h1>Google search</h1>");
				_writer.println();
				_writer.print("<p>Open the page <b>https://www.google.com</b> in a browser:</p>");
				_writer.println();
				driver.navigate().to("https://www.google.com");
				capture();
				_writer.print("<p>Accept cookies:</p>");
				_writer.println();
				Click(new PageElement(_page, "button", "L2AGLb"));
				String searchText = "jwst";
				_writer.print("<p>Type <b>");
				_writer.print(searchText);
				_writer.print("</b> into the search field:</p>");
				_writer.println();
				Fill(new PageElement(_page, "textarea", "q"), searchText);
				PageElement searchButton = new PageElement(_page, "input", "btnK");
				_writer.print("<p>Finally, click the <b>");
				_writer.print(searchButton.getText());
				_writer.print("</b> button:</p>");
				_writer.println();
				Click(searchButton);
				sleep(2);
				_writer.print("<p>And now you can see the search results:</p>");
				_writer.println();
				capture();
			} finally {
				_writer.close();
				_writer = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}				
	}
	
	
	private void Click(PageElement e) {
		var _pageStack = new ArrayList<Page>();
		var _page = new Page(driver, driver);
		capture(e);
		e.click();
	}
	private void Fill(PageElement e, String text) {
		var _pageStack = new ArrayList<Page>();
		var _page = new Page(driver, driver);
		e.fill(text);
		capture(e);
	}
	
}
