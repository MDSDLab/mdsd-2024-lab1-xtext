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

public class JavaScriptTest extends SeleniumTest {
	private static Logger logger = LoggerFactory.getLogger(JavaScriptTest.class);

	@Test
	public void largeModalTest() {
		var _pageStack = new ArrayList<Page>();
		var _page = new Page(driver, driver);
		driver.navigate().to("https://getbootstrap.com/docs/4.0/components/modal/");
		_pageStack.add(_page);
		var _page1 = new Bootstrap(driver, _page.getContext());
		_page = _page1;
		HighlightAndClick(_page1.getLargeModal());
		_pageStack.add(_page);
		var _page2 = new ModalDialog(driver, _page1.getLargeDialog().find());
		_page = _page2;
		HighlightAndClick(_page2.getClose());
		_page = _pageStack.remove(_pageStack.size()-1);
		_page = _pageStack.remove(_pageStack.size()-1);
	}
	
	
	
	private void HighlightAndClick(PageElement e) {
		var _pageStack = new ArrayList<Page>();
		var _page = new Page(driver, driver);
		javascript.executeScript("arguments[0].style.outline = 'red solid 4px'; arguments[0].style.outlineOffset = '-4px'; arguments[0].scrollIntoView({ block: 'center', inline: 'center' });", e.find());
		sleep(1);
		javascript.executeScript("arguments[0].style.outline = ''; arguments[0].style.outlineOffset = '';", e.find());
		e.click();
	}
	
	private static class Bootstrap extends Page {
		private PageElement largeModal;
		private PageElement largeDialog;
		
		public Bootstrap(WebDriver driver, SearchContext context) {
			super(driver, context);
			largeModal = new PageElement(this, "button", "Large modal");
			largeDialog = new PageElement(this, "div", "myLargeModalLabel");
		}
		
		
		public PageElement getLargeModal() {
			return this.largeModal;
		}
		public PageElement getLargeDialog() {
			return this.largeDialog;
		}
	}
	private static class ModalDialog extends Page {
		private PageElement close;
		
		public ModalDialog(WebDriver driver, SearchContext context) {
			super(driver, context);
			close = new PageElement(this, "button", "Close");
		}
		
		
		public PageElement getClose() {
			return this.close;
		}
	}
}
