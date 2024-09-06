package webtest.dsl.tests

import org.junit.jupiter.api.Assertions
import webtest.dsl.WebTestParser

class ParseTestBase {

	def void parse(CharSequence code) {
		val parser = new WebTestParser(code)
		val result = parser.model
		Assertions.assertNotNull(result)
		val errors = result.eResource.errors
		Assertions.assertTrue(errors.isEmpty, '''Unexpected errors: «errors.join(", ")»''')
	}	
}