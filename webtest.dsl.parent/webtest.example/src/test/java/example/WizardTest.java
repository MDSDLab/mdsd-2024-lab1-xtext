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

public class WizardTest extends SeleniumTest {
	private static Logger logger = LoggerFactory.getLogger(WizardTest.class);

	
	
	@Test
	public void body() {
		var _pageStack = new ArrayList<Page>();
		var _page = new Page(driver, driver);
		driver.navigate().to("https://www.w3schools.com/howto/howto_js_form_steps.asp");
		sleep(1);
		PageElement acceptCookies = new PageElement(_page, "div", "accept-choices");
		if (acceptCookies.exists()) {
			acceptCookies.click();
		}
		while (new PageElement(_page, "button", "Next").exists()) {
			_pageStack.add(_page);
			var _page1 = new Name(driver, _page.getContext());
			_page = _page1;
			if (_page1.getFirstName().exists()) {
				_page1.getFirstName().fill("Agent");
				_page1.getLastName().fill("Smith");
				assertEquals("Smith".toString(), _page1.getLastName().toString());
			}
			_page = _pageStack.remove(_pageStack.size()-1);
			_pageStack.add(_page);
			var _page2 = new ContactInfo(driver, _page.getContext());
			_page = _page2;
			if (_page2.getEmail().exists()) {
				_page2.getEmail().fill("smith@matrix.com");
				_page2.getPhone().fill("5551234");
				assertTrue(_page2.getEmail().getText().contains("smith"));
			}
			_page = _pageStack.remove(_pageStack.size()-1);
			_pageStack.add(_page);
			var _page3 = new Birthday(driver, _page.getContext());
			_page = _page3;
			if (_page3.getDay().exists()) {
				_page3.getDay().fill("01");
				_page3.getMonth().fill("01");
				_page3.getYear().fill("2000");
			}
			_page = _pageStack.remove(_pageStack.size()-1);
			_pageStack.add(_page);
			var _page4 = new LoginInfo(driver, _page.getContext());
			_page = _page4;
			if (_page4.getUsername().exists()) {
				_page4.getUsername().fill("smith");
				_page4.getPassword().fill("secret");
			}
			_page = _pageStack.remove(_pageStack.size()-1);
			sleep(1);
			new PageElement(_page, "button", "Next").click();
		}
	}
	
	
	private static class Name extends Page {
		private PageElement firstName;
		private PageElement lastName;
		
		public Name(WebDriver driver, SearchContext context) {
			super(driver, context);
			firstName = new PageElement(this, "input", "First name...");
			lastName = new PageElement(this, "input", "Last name...");
		}
		
		
		public PageElement getFirstName() {
			return this.firstName;
		}
		public PageElement getLastName() {
			return this.lastName;
		}
	}
	private static class ContactInfo extends Page {
		private PageElement email;
		private PageElement phone;
		
		public ContactInfo(WebDriver driver, SearchContext context) {
			super(driver, context);
			email = new PageElement(this, "input", "E-mail...");
			phone = new PageElement(this, "input", "Phone...");
		}
		
		
		public PageElement getEmail() {
			return this.email;
		}
		public PageElement getPhone() {
			return this.phone;
		}
	}
	private static class Birthday extends Page {
		private PageElement day;
		private PageElement month;
		private PageElement year;
		
		public Birthday(WebDriver driver, SearchContext context) {
			super(driver, context);
			day = new PageElement(this, "input", "dd");
			month = new PageElement(this, "input", "mm");
			year = new PageElement(this, "input", "yyyy");
		}
		
		
		public PageElement getDay() {
			return this.day;
		}
		public PageElement getMonth() {
			return this.month;
		}
		public PageElement getYear() {
			return this.year;
		}
	}
	private static class LoginInfo extends Page {
		private PageElement username;
		private PageElement password;
		
		public LoginInfo(WebDriver driver, SearchContext context) {
			super(driver, context);
			username = new PageElement(this, "input", "Username...");
			password = new PageElement(this, "input", "Password...");
		}
		
		
		public PageElement getUsername() {
			return this.username;
		}
		public PageElement getPassword() {
			return this.password;
		}
	}
}
