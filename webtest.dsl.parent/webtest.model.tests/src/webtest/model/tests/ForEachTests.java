package webtest.model.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import webtest.model.ModelFactory;

public class ForEachTests {
	static ModelFactory f;
	
	@BeforeAll
	static void startup() {
		f = ModelFactory.eINSTANCE;
	}
	
	@Test
	void testForEach() {
		var main = f.createMain();
		main.getTestClass().add("example");
		main.getTestClass().add("ExampleTest");
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var s1 = f.createForEachStatement();
		var e1 = f.createElementExpression();
		e1.setTag("input");
		e1.setLabel("q");
		s1.setItems(e1);
		var v1 = f.createVariable();
		v1.setName("v1");
		s1.setItem(v1);
		var bs1 = f.createBlockStatement();
		s1.setBody(bs1);
		bs0.getStatements().add(s1);
	}
}
