package webtest.model.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import webtest.model.ModelFactory;
import webtest.model.Type;

public class MetaModelTests {
	static ModelFactory f;
	
	@BeforeAll
	static void startup() {
		f = ModelFactory.eINSTANCE;
	}
	
	@Test
	void structure() {
		var main = f.createMain();
		main.getTestClass().add("example");
		main.getTestClass().add("ExampleTest");
		var bs1 = f.createBlockStatement();
		main.setBody(bs1);

		var page = f.createPage();
		main.getDeclarations().add(page);

		var v1 = f.createVariable();
		v1.setType(Type.INTEGER);
		v1.setName("v1");
		page.getVariables().add(v1);
		var e1 = f.createIntegerExpression();
		e1.setValue(5);
		v1.setValue(e1);

		var testCase = f.createTestCase();
		main.getDeclarations().add(testCase);
		var bs2 = f.createBlockStatement();
		testCase.setBody(bs2);

		var op1 = f.createOperation();
		main.getDeclarations().add(op1);
		var bs3 = f.createBlockStatement();
		op1.setBody(bs3);

		var op2 = f.createOperation();
		page.getOperations().add(op2);
		var bs4 = f.createBlockStatement();
		op2.setBody(bs4);
}
	
	@Test
	void controlStatements() {
		var main = f.createMain();
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var ifs = f.createIfStatement();
		bs0.getStatements().add(ifs);
		var bs1 = f.createBlockStatement();
		ifs.setThen(bs1);
		var bs2 = f.createBlockStatement();
		ifs.setElse(bs2);
		var e1 = f.createBooleanExpression();
		ifs.setCondition(e1);

		var ws = f.createWhileStatement();
		bs0.getStatements().add(ws);
		var e2 = f.createBooleanExpression();
		ws.setCondition(e2);

		var vs = f.createVariableStatement();
		bs0.getStatements().add(vs);
		var v1 = f.createVariable();
		vs.setVariable(v1);
		var e3 = f.createStringExpression();
		v1.setType(Type.STRING);
		v1.setName("v1");
		v1.setValue(e3);
	}
	
	@Test
	void htmlStatements() {
		var main = f.createMain();
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var e1 = f.createStringExpression();
		e1.setValue("https://www.google.com");
		var s1 = f.createOpenStatement();
		s1.setUrl(e1);
		bs0.getStatements().add(s1);
		
		var e2 = f.createElementExpression();
		e2.setTag("input");
		e2.setLabel("q");
		var e3 = f.createStringExpression();
		e3.setValue("jwst");
		var s2 = f.createFillStatement();
		s2.setElement(e2);
		s2.setValue(e3);
		bs0.getStatements().add(s2);
		
		var e4 = f.createElementExpression();
		e4.setTag("button");
		e4.setLabel("Search");
		var s3 = f.createClickStatement();
		s3.setElement(e4);
		bs0.getStatements().add(s3);
		
		var p1 = f.createPage();
		p1.setName("Cookie");
		var e5 = f.createElementExpression();
		e5.setTag("button");
		e5.setLabel("Accept");
		var v1 = f.createVariable();
		v1.setType(Type.ELEMENT);
		v1.setName("accept");
		v1.setValue(e5);
		p1.getVariables().add(v1);
		main.getDeclarations().add(p1);
		
		var s4 = f.createContextStatement();
		s4.setPage(p1);
		var bs1 = f.createBlockStatement();
		s4.setBody(bs1);
		var e6 = f.createElementExpression();
		e6.setTag("div");
		e6.setLabel("popup");
		s4.setElement(e6);
		bs0.getStatements().add(s4);
	}
	
	@Test
	void otherStatements() {
		var main = f.createMain();
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var op1 = f.createOperation();
		op1.setName("login");
		var p1 = f.createVariable();
		p1.setType(Type.STRING);
		p1.setName("username");
		op1.getParameters().add(p1);
		var p2 = f.createVariable();
		p2.setType(Type.STRING);
		p2.setName("password");
		op1.getParameters().add(p2);
		main.getDeclarations().add(op1);
		
		var s1 = f.createCallStatement();
		s1.setOperation(op1);
		var e1 = f.createStringExpression();
		e1.setValue("Alice");
		s1.getArguments().add(e1);
		var e2 = f.createStringExpression();
		e2.setValue("secret");
		s1.getArguments().add(e2);
		bs0.getStatements().add(s1);
		
		var s2 = f.createPrintStatement();
		var e3 = f.createStringExpression();
		e3.setValue("logged in");
		s2.getValues().add(e3);
		bs0.getStatements().add(s2);
		
		var s3 = f.createWaitStatement();
		var e4 = f.createBooleanExpression();
		e4.setValue(true);
		var e5 = f.createIntegerExpression();
		e5.setValue(5);
		s3.setCondition(e4);
		s3.setSeconds(e5);
		bs0.getStatements().add(s3);
		
		var s4 = f.createAssertStatement();
		var e6 = f.createBooleanExpression();
		e6.setValue(false);
		s4.setCondition(e6);
		bs0.getStatements().add(s4);
	}
	
	@Test
	void complexExpressions() {
		var main = f.createMain();
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var s1 = f.createPrintStatement();
		bs0.getStatements().add(s1);
		
		var e1 = f.createIsExpression();
		var e2 = f.createElementExpression();
		e2.setTag("input");
		e2.setLabel("q");
		var e3 = f.createStringExpression();
		e3.setValue("jwst");
		e1.setLeft(e2);
		e1.setRight(e3);
		s1.getValues().add(e1);
		
		var e4 = f.createContainsExpression();
		var e5 = f.createElementExpression();
		e5.setTag("input");
		e5.setLabel("q");
		var e6 = f.createStringExpression();
		e6.setValue("jwst");
		e4.setLeft(e5);
		e4.setRight(e6);
		s1.getValues().add(e4);
		
		var e7 = f.createExistsExpression();
		var e8 = f.createElementExpression();
		e8.setTag("input");
		e8.setLabel("q");
		e7.setOperand(e8);
		s1.getValues().add(e7);
		
		var e9 = f.createNotExpression();
		var e10 = f.createBooleanExpression();
		e10.setValue(false);
		e9.setOperand(e10);
		s1.getValues().add(e9);
	}
	
	@Test
	void simpleExpressions() {
		var main = f.createMain();
		var bs0 = f.createBlockStatement();
		main.setBody(bs0);
		
		var s1 = f.createPrintStatement();
		bs0.getStatements().add(s1);
		
		var e1 = f.createElementExpression();
		e1.setTag("input");
		e1.setLabel("q");
		s1.getValues().add(e1);

		var e2 = f.createStringExpression();
		e2.setValue("jwst");
		s1.getValues().add(e2);

		var e3 = f.createIntegerExpression();
		e3.setValue(5);
		s1.getValues().add(e3);

		var e4 = f.createBooleanExpression();
		e4.setValue(true);
		s1.getValues().add(e4);
		
		var s2 = f.createVariableStatement();
		var v1 = f.createVariable();
		v1.setType(Type.ELEMENT);
		v1.setName("v1");
		var e5 = f.createElementExpression();
		e5.setTag("input");
		e5.setLabel("q");
		v1.setValue(e5);
		s2.setVariable(v1);
		bs0.getStatements().add(s2);
		
		var s3 = f.createVariableStatement();
		var v2 = f.createVariable();
		v2.setType(Type.STRING);
		v2.setName("v2");
		var e6 = f.createStringExpression();
		e6.setValue("jwst");
		v2.setValue(e6);
		s3.setVariable(v2);
		bs0.getStatements().add(s3);
		
		var s4 = f.createVariableStatement();
		var v3 = f.createVariable();
		v3.setType(Type.INTEGER);
		v3.setName("v3");
		var e7 = f.createIntegerExpression();
		e7.setValue(13);
		v3.setValue(e7);
		s4.setVariable(v3);
		bs0.getStatements().add(s4);
		
		var s5 = f.createVariableStatement();
		var v4 = f.createVariable();
		v4.setType(Type.BOOLEAN);
		v4.setName("v4");
		var e8 = f.createBooleanExpression();
		e8.setValue(true);
		v4.setValue(e8);
		s5.setVariable(v4);
		bs0.getStatements().add(s5);
	}
}
