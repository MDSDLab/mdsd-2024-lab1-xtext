package webtest.model.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import webtest.model.ModelFactory;

public class JavaScriptTests {
	static ModelFactory f;
	
	@BeforeAll
	static void startup() {
		f = ModelFactory.eINSTANCE;
	}
	
	@Test
	void testJavaScript() {
		var main = f.createMain();
		main.getTestClass().add("example");
		main.getTestClass().add("ExampleTest");
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var s1 = f.createJavaScriptStatement();
		var e1 = f.createStringExpression();
		e1.setValue("arguments[0].value = arguments[1];");
		s1.setCode(e1);
		var e2 = f.createElementExpression();
		e2.setTag("input");
		e2.setLabel("q");
		s1.getArguments().add(e2);
		var e3 = f.createStringExpression();
		e3.setValue("jwst");
		s1.getArguments().add(e3);
		bs0.getStatements().add(s1);
	}
}
