package webtest.dsl.ui.tests;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.testing.AbstractOutlineTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import webtest.dsl.WebTestExtensions;

@ExtendWith(InjectionExtension.class)
@InjectWith(WebTestDslUiInjectorProvider.class)
public class OutlineTests extends AbstractOutlineTest {
	
	@Test
	public void testOutline() throws Exception {
		var code = 
		"""
		webtest example.WizardTest
		
		page LoginPage
			element username = input "username"
			element password = input "password"
			element loginButton = button "Log in"
		  
			operation login(string u, string p)
				fill username with u
				fill password with p
				click loginButton
			end
		end
		
		test LoginTest
			open "https://example.com/login"
			context as LoginPage
				login using "Alice", "a"
			end
		end
		
		operation sayHello
			print "hello"
		end
		
		print "world"
		""";
		
		var outline = getOutlineTree(code);
		Assertions.assertEquals(3, outline.getChildren().size());

		var loginPage = outline.getChildren().get(0);
		checkNode(loginPage, "LoginPage", 4);
		checkNode(loginPage.getChildren().get(0), "username: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(1), "password: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(2), "loginButton: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(3), "login(u: STRING, p: STRING)", 0);

		checkNode(outline.getChildren().get(1), "LoginTest", 0);
		checkNode(outline.getChildren().get(2), "sayHello()", 0);
		
	}
	
	@Test
	public void testOutlineWithManual() throws Exception {
		if (!WebTestExtensions.ENABLE_MANUAL) return;
		var code = 
		"""
		webtest example.WizardTest
		
		page LoginPage
			element username = input "username"
			element password = input "password"
			element loginButton = button "Log in"
		  
			operation login(string u, string p)
				fill username with u
				fill password with p
				click loginButton
			end
		end
		
		manual Search
			element q = input "q"
			element search = button "search"
			open "https://www.google.com"
			print "Type 'jwst' to the search field:"
			fill q with "jwst"
			print "Click the 'search' button:"
			click search
		end
		
		test LoginTest
			open "https://example.com/login"
			context as LoginPage
				login using "Alice", "a"
			end
		end
		
		operation sayHello
			print "hello"
		end
		
		print "world"
		""";
		
		var outline = getOutlineTree(code);
		Assertions.assertEquals(4, outline.getChildren().size());

		var loginPage = outline.getChildren().get(0);
		checkNode(loginPage, "LoginPage", 4);
		checkNode(loginPage.getChildren().get(0), "username: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(1), "password: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(2), "loginButton: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(3), "login(u: STRING, p: STRING)", 0);

		checkNode(outline.getChildren().get(1), "Search", 0);
		checkNode(outline.getChildren().get(2), "LoginTest", 0);
		checkNode(outline.getChildren().get(3), "sayHello()", 0);
	}
	
	@Test
	public void testOutlineWithTestParams() throws Exception {
		if (!WebTestExtensions.ENABLE_TEST_PARAMS) return;
		var code = 
		"""
		webtest example.WizardTest
		
		page LoginPage
			element username = input "username"
			element password = input "password"
			element loginButton = button "Log in"
		  
			operation login(string u, string p)
				fill username with u
				fill password with p
				click loginButton
			end
		end
		
		test LoginTest(string u, string p)
		with "Alice","a"
		with "Bob","b"
			open "https://example.com/login"
			context as LoginPage
				login using u,p
			end
		end
		
		operation sayHello
			print "hello"
		end
		
		print "world"
		""";
		
		var outline = getOutlineTree(code);
		Assertions.assertEquals(3, outline.getChildren().size());

		var loginPage = outline.getChildren().get(0);
		checkNode(loginPage, "LoginPage", 4);
		checkNode(loginPage.getChildren().get(0), "username: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(1), "password: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(2), "loginButton: ELEMENT", 0);
		checkNode(loginPage.getChildren().get(3), "login(u: STRING, p: STRING)", 0);

		checkNode(outline.getChildren().get(1), "LoginTest(u: STRING, p: STRING)", 0);
		checkNode(outline.getChildren().get(2), "sayHello()", 0);
	}
	
	private void checkNode(IOutlineNode node, String label, int children) {
		Assertions.assertEquals(label, getNodeText(node));
		Assertions.assertEquals(children, node.getChildren().size());
	} 
}