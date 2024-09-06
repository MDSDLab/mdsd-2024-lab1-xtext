package webtest.dsl.tests

import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Map
import org.eclipse.xtext.EcoreUtil2
import webtest.dsl.WebTestParser
import webtest.model.Main
import webtest.model.Variable
import webtest.model.VariableExpression
import static org.junit.jupiter.api.Assertions.*

class NameAnalysisTestBase extends TestBase {
		
	def Main assertNames(CharSequence code, Map<Integer, List<Integer>> variables) {
		assertNames(code, variables, #[])
	}
	
	def Main assertNames(CharSequence code, Map<Integer, List<Integer>> variables, Iterable<String> expectedErrors) {
		val parser = new WebTestParser(code)
		val hasAnyErrors = expectedErrors.size > 0
		if (hasAnyErrors) {
			assertErrors(parser, expectedErrors)
		} else {
			if (parser.hasAnyErrors) {
				assertTrue(false, "Unexpected error: "+parser.firstError)
			}
		}
		val varDefs = EcoreUtil2.getAllContentsOfType(parser.model, Variable);
		val varRefs = EcoreUtil2.getAllContentsOfType(parser.model, VariableExpression)
		var invalidDefs = new HashSet<Integer>(variables.keySet)
		for (var vi = 0; vi < varDefs.length; vi++) {
			val v = varDefs.get(vi)
			if (variables.containsKey(vi)) {
				invalidDefs.remove(vi)
				val expectedRefs = variables.get(vi)
				val actualRefs = varRefs.filter[it.variable === v].map[varRefs.indexOf(it)]
				val invalidRefs = new ArrayList<Integer>()
				val missingRefs = new ArrayList<Integer>()
				missingRefs.addAll(actualRefs)
				for (var i = 0; i < expectedRefs.size; i++) {
					val ref = expectedRefs.get(i)
					if (missingRefs.contains(ref)) {
						missingRefs.remove(ref)
					} else {
						invalidRefs.add(ref)
					}
				}
				if (missingRefs.size > 0) {
					assertTrue(false, "Missing variable reference: "+vi+" -> #["+missingRefs.get(0)+"] ("+v.name+" -> #["+varRefs.get(missingRefs.get(0)).variable?.name+"]).")
				}
				if (invalidRefs.size > 0) {
					if (invalidRefs.get(0) < varRefs.size) {
						assertTrue(false, "Invalid variable reference: "+vi+" -> #["+invalidRefs.get(0)+"] ("+v.name+" -> #["+varRefs.get(invalidRefs.get(0)).variable?.name+"]).")
					} else {
						assertTrue(false, "Invalid variable reference index: "+vi+" -> #["+invalidRefs.get(0)+"] ("+v.name+" -> #[?]).")
					}
				}
			} else {
				assertTrue(false, "Missing variable definition: "+vi+" -> #[...] ("+v.name+" -> #[...]).")
			}
		}
		if (invalidDefs.size > 0) {
			assertTrue(false, "Invalid variable definition index: "+invalidDefs.head+" -> #[...].")
		}
		return parser.model
	}
	
}