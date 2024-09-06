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

public class CaptureTest extends SeleniumTest {
	private static Logger logger = LoggerFactory.getLogger(CaptureTest.class);

	@Test
	public void largeModalTest() {
		var _pageStack = new ArrayList<Page>();
		var _page = new Page(driver, driver);
		driver.navigate().to("https://getbootstrap.com/docs/4.0/components/modal/");
		_pageStack.add(_page);
		var _page1 = new Bootstrap(driver, _page.getContext());
		_page = _page1;
		capture(_page1.getLargeModal());
		_page1.getLargeModal().click();
		_pageStack.add(_page);
		var _page2 = new ModalDialog(driver, _page1.getLargeDialog().find());
		_page = _page2;
		capture(_page2.getClose());
		_page2.getClose().click();
		_page = _pageStack.remove(_pageStack.size()-1);
		_page = _pageStack.remove(_pageStack.size()-1);
	}
	@Test
	public void smallModalTest() {
		var _pageStack = new ArrayList<Page>();
		var _page = new Page(driver, driver);
		driver.navigate().to("https://getbootstrap.com/docs/4.0/components/modal/");
		_pageStack.add(_page);
		var _page1 = new Bootstrap(driver, _page.getContext());
		_page = _page1;
		capture(_page1.getSmallModal());
		_page1.getSmallModal().click();
		_pageStack.add(_page);
		var _page2 = new ModalDialog(driver, _page1.getSmallDialog().find());
		_page = _page2;
		capture(_page2.getClose());
		_page2.getClose().click();
		_page = _pageStack.remove(_pageStack.size()-1);
		_page = _pageStack.remove(_pageStack.size()-1);
	}
	
	
	
	
	private static class Bootstrap extends Page {
		private PageElement largeModal;
		private PageElement smallModal;
		private PageElement largeDialog;
		private PageElement smallDialog;
		
		public Bootstrap(WebDriver driver, SearchContext context) {
			super(driver, context);
			largeModal = new PageElement(this, "button", "Large modal");
			smallModal = new PageElement(this, "button", "Small modal");
			largeDialog = new PageElement(this, "div", "myLargeModalLabel");
			smallDialog = new PageElement(this, "div", "mySmallModalLabel");
		}
		
		
		public PageElement getLargeModal() {
			return this.largeModal;
		}
		public PageElement getSmallModal() {
			return this.smallModal;
		}
		public PageElement getLargeDialog() {
			return this.largeDialog;
		}
		public PageElement getSmallDialog() {
			return this.smallDialog;
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
