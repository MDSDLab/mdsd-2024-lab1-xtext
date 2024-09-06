package webtest.model.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import webtest.model.ModelFactory;

public class CaptureTests {
	static ModelFactory f;
	
	@BeforeAll
	static void startup() {
		f = ModelFactory.eINSTANCE;
	}
	
	@Test
	void testCapture() {
		var main = f.createMain();
		main.getTestClass().add("example");
		main.getTestClass().add("ExampleTest");
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var s1 = f.createCaptureStatement();
		var e1 = f.createElementExpression();
		e1.setTag("input");
		e1.setLabel("q");
		s1.setElement(e1);
		bs0.getStatements().add(s1);
	}
}
