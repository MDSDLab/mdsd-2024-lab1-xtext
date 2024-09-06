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

