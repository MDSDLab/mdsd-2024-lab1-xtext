package webtest.model.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import webtest.model.ModelFactory;

public class ManualTests {
	static ModelFactory f;
	
	@BeforeAll
	static void startup() {
		f = ModelFactory.eINSTANCE;
	}
	
	@Test
	void testManual() {
		var main = f.createMain();
		main.getTestClass().add("example");
		main.getTestClass().add("ExampleTest");
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var m1 = f.createManual();
		m1.setName("m1");
		var bs1 = f.createBlockStatement();
		m1.setBody(bs1);
		main.getDeclarations().add(m1);
	}
}
