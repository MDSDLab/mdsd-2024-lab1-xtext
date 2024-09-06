package webtest.dsl.tests

import java.util.ArrayList
import org.eclipse.xtext.diagnostics.Severity
import webtest.dsl.WebTestParser
import webtest.model.Main

import static org.junit.jupiter.api.Assertions.*

class TestBase {
	def Main assertErrors(CharSequence code, Iterable<String> expectedErrors) {
		val parser = new WebTestParser(code)
		assertErrors(parser, expectedErrors)
	}
	
	def Main assertErrors(WebTestParser parser, Iterable<String> expectedErrors) {
		val actalErrors = parser.issues.filter[it.severity == Severity.ERROR].map[it.message].toList
		val unusedActualErrors = new ArrayList<String>(actalErrors)
		val unusedExpectedErrors = new ArrayList<String>()
		unusedExpectedErrors.addAll(expectedErrors);
		for (ae: actalErrors) {
			unusedExpectedErrors.remove(ae)
		}
		for (ae: expectedErrors) {
			unusedActualErrors.remove(ae)
		}
		if (unusedActualErrors.size > 0) {
			assertTrue(false, "Unexpected error: "+unusedActualErrors.get(0))
		}
		if (unusedExpectedErrors.size > 0) {
			assertTrue(false, "Expected error missing: "+unusedExpectedErrors.get(0))
		}
		return parser.model
	}

}