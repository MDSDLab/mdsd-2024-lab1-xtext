package webtest.dsl.tests

import java.util.HashSet
import java.util.Map
import org.eclipse.xtext.EcoreUtil2
import webtest.dsl.WebTestParser
import webtest.model.Main
import webtest.model.Type
import webtest.model.VariableExpression
import static org.junit.jupiter.api.Assertions.*

class TypeAnalysisTestBase extends TestBase {
	
	def Main assertTypes(CharSequence code, Map<String, Type> actualTypes, Map<String, Type> expectedTypes) {
		assertTypes(code, actualTypes, expectedTypes, #[])
	}
	
	def Main assertTypes(CharSequence code, Map<String, Type> actualTypes, Map<String, Type> expectedTypes, Iterable<String> expectedErrors) {
		val parser = new WebTestParser(code)
		val main = parser.model
		main.computeTypes()
		val hasAnyErrors = expectedErrors.size > 0
		if (hasAnyErrors) {
			assertErrors(parser, expectedErrors)
		} else {
			if (parser.hasAnyErrors) {
				assertTrue(false, "Unexpected error: "+parser.firstError)
			}
		}
		val codeVariables = EcoreUtil2.getAllContentsOfType(parser.model, VariableExpression);
		val missingVariables = new HashSet<String>(actualTypes.keySet) 
		for (ve: codeVariables) {
			val v = ve.variable
			if (actualTypes.containsKey(v.name)) {
				missingVariables.remove(v.name)
				val expectedActualType = actualTypes.get(v.name)
				if (ve.actualType != expectedActualType) {
					assertTrue(false, "Variable '"+v.name+"' must have an actual type "+expectedActualType+", but the compiler computed actual type is "+ve.actualType+".")
				}
			}
		}
		for (v: missingVariables) {
			assertTrue(false, "Variable '"+v+"' was not created by the compiler.")
		}
		missingVariables.clear
		missingVariables.addAll(expectedTypes.keySet)
		for (ve: codeVariables) {
			val v = ve.variable
			if (expectedTypes.containsKey(v.name)) {
				missingVariables.remove(v.name)
				val expectedExpectedType = expectedTypes.get(v.name)
				if (ve.expectedType != expectedExpectedType) {
					assertTrue(false, "Variable '"+v.name+"' must have an expected type "+expectedExpectedType+", but the compiler computed expected type is "+ve.expectedType+".")
				}
			}
		}
		for (v: missingVariables) {
			assertTrue(false, "Variable '"+v+"' was not created by the compiler.")
		}
		return main
	}	
}