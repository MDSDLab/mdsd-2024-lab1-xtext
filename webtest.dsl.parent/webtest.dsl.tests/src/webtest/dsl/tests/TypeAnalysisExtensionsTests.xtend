package webtest.dsl.tests

import org.junit.jupiter.api.Test
import webtest.dsl.WebTestExtensions
import webtest.model.Type

class TypeAnalysisExtensionsTests extends TypeAnalysisTestBase {

	@Test
	def void testCapture() {
		if (!WebTestExtensions.ENABLE_CAPTURE) return;
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(element a, string b)
		    capture a
		    capture b
		end
		''',
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING
		},
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.ELEMENT
		},
		#[
		"Expression of type ELEMENT expected but STRING was found."
		]);
	}	
	
		
	@Test
	def void testForEach() {
		if (!WebTestExtensions.ENABLE_FOR_EACH) return;
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(element a, string b)
		    foreach i in a
		    	print i
		    end
		    foreach j in b
		    	print j
		    end
		end
		''',
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING,
		"i" -> Type.ELEMENT,
		"j" -> Type.ELEMENT
		},
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.ELEMENT,
		"i" -> Type.ANY,
		"j" -> Type.ANY
		},
		#[
		"Expression of type ELEMENT expected but STRING was found."
		]);
	}	
		
	@Test
	def void testJavaScript() {
		if (!WebTestExtensions.ENABLE_JAVA_SCRIPT) return;
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(string a, integer b, boolean c)
		    javascript a using b, c
		end

		operation op2(element d, integer e, boolean f)
		    javascript d using e, f
		end
		''',
		#{
		"a" -> Type.STRING,
		"b" -> Type.INTEGER,
		"c" -> Type.BOOLEAN,
		"d" -> Type.ELEMENT,
		"e" -> Type.INTEGER,
		"f" -> Type.BOOLEAN
		},
		#{
		"a" -> Type.STRING,
		"b" -> Type.ANY,
		"c" -> Type.ANY,
		"d" -> Type.STRING,
		"e" -> Type.ANY,
		"f" -> Type.ANY
		},
		#[
		"Expression of type STRING expected but ELEMENT was found."
		]);
	}	
		
	@Test
	def void testManual() {
		if (!WebTestExtensions.ENABLE_MANUAL) return;
		assertTypes(
		'''
		webtest example.ExampleTest

		manual m1
		  string a = "hello"
		  fill input "q" with a
		  click button "ok"
		end
		''',
		#{
		"a" -> Type.STRING
		},
		#{
		"a" -> Type.STRING
		});
	}
	
	@Test
	def void testTestParams() {
		if (!WebTestExtensions.ENABLE_TEST_PARAMS) return;
		assertTypes(
		'''
		webtest example.ExampleTest

		test t1(element a, string b, integer c, boolean d)
		with input "q", "hello", 5, true
		with true, input "q", "hello", 5
		  fill a with b
		  wait c seconds until d
		end
		''',
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING,
		"c" -> Type.INTEGER,
		"d" -> Type.BOOLEAN
		},
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING,
		"c" -> Type.INTEGER,
		"d" -> Type.BOOLEAN
		},
		#[
		"Expression of type ELEMENT expected but BOOLEAN was found.",
		"Expression of type STRING expected but ELEMENT was found.",
		"Expression of type INTEGER expected but STRING was found.",
		"Expression of type BOOLEAN expected but INTEGER was found."
		]);
	}	
}