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
