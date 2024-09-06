package webtest.model.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import webtest.model.ModelFactory;
import webtest.model.Type;

public class TestParamsTests {
	static ModelFactory f;
	
	@BeforeAll
	static void startup() {
		f = ModelFactory.eINSTANCE;
	}
	
	@Test
	void testTestParams() {
		var main = f.createMain();
		main.getTestClass().add("example");
		main.getTestClass().add("ExampleTest");
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var t1 = f.createTestCase();
		var v1 = f.createVariable();
		v1.setType(Type.STRING);
		v1.setName("username");
		t1.getParameters().add(v1);
		var v2 = f.createVariable();
		v2.setType(Type.STRING);
		v2.setName("password");
		t1.getParameters().add(v2);
		
		var tci1 = f.createTestCaseInstance();
		var e1 = f.createStringExpression();
		e1.setValue("Alice");
		tci1.getArguments().add(e1);
		var e2 = f.createStringExpression();
		e2.setValue("secretA");
		tci1.getArguments().add(e2);
		t1.getInstances().add(tci1);

		var tci2 = f.createTestCaseInstance();
		var e3 = f.createStringExpression();
		e3.setValue("Bob");
		tci2.getArguments().add(e3);
		var e4 = f.createStringExpression();
		e4.setValue("secretB");
		tci2.getArguments().add(e4);
		t1.getInstances().add(tci2);

		var bs1 = f.createBlockStatement();
		t1.setBody(bs1);
		main.getDeclarations().add(t1);
	}
}
