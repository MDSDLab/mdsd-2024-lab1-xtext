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

public class TestParamsTest extends SeleniumTest {
	private static Logger logger = LoggerFactory.getLogger(TestParamsTest.class);

	@ParameterizedTest
	@CsvSource({"2,3,6", "4,7,28"})
	public void MultiplicationTest(String left, String right, String result) {
		var _pageStack = new ArrayList<Page>();
		var _page = new Page(driver, driver);
		driver.navigate().to("https://www.theonlinecalculator.com/");
		_pageStack.add(_page);
		var _page1 = new Calculator(driver, _page.getContext());
		_page = _page1;
		_page1.multiply(left, right);
		assertEquals(result.toString(), _page1.getDisplay().toString());
		capture();
		_page = _pageStack.remove(_pageStack.size()-1);
	}
	
	
	
	
	private static class Calculator extends Page {
		private PageElement display;
		private PageElement clear;
		private PageElement add;
		private PageElement subtract;
		private PageElement multiply;
		private PageElement divide;
		private PageElement compute;
		
		public Calculator(WebDriver driver, SearchContext context) {
			super(driver, context);
			display = new PageElement(this, "input", "display");
			clear = new PageElement(this, "input", "AC");
			add = new PageElement(this, "input", "+");
			subtract = new PageElement(this, "input", "-");
			multiply = new PageElement(this, "input", "×");
			divide = new PageElement(this, "input", "/");
			compute = new PageElement(this, "input", "=");
		}
		
		public void binaryOperation(String left, PageElement op, String right) {
			var _pageStack = new ArrayList<Page>();
			var _page = this;
			this.getClear().click();
			this.getDisplay().fill(left);
			op.click();
			this.getDisplay().fill(right);
			this.getCompute().click();
		}
		public void multiply(String left, String right) {
			var _pageStack = new ArrayList<Page>();
			var _page = this;
			this.binaryOperation(left, this.getMultiply(), right);
		}
		
		public PageElement getDisplay() {
			return this.display;
		}
		public PageElement getClear() {
			return this.clear;
		}
		public PageElement getAdd() {
			return this.add;
		}
		public PageElement getSubtract() {
			return this.subtract;
		}
		public PageElement getMultiply() {
			return this.multiply;
		}
		public PageElement getDivide() {
			return this.divide;
		}
		public PageElement getCompute() {
			return this.compute;
		}
	}
}
