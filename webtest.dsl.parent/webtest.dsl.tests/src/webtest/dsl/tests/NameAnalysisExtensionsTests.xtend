package webtest.dsl.tests

import org.eclipse.xtext.EcoreUtil2
import org.junit.jupiter.api.Test
import webtest.dsl.WebTestExtensions
import webtest.model.PrintStatement
import webtest.model.Type
import webtest.model.Variable
import webtest.model.VariableExpression

import static org.junit.jupiter.api.Assertions.*

class NameAnalysisExtensionsTests extends NameAnalysisTestBase {
	@Test
	def void testCapture() {
		if (!WebTestExtensions.ENABLE_CAPTURE) return;
		assertNames(
		'''
		webtest example.ExampleTest

		operation op1(element a)
		    capture a
		end
		
		operation op2
		    capture page
		end
		''',
		#{
		0 -> #[0]
		});
	}
	
	@Test
	def void testForEach() {
		if (!WebTestExtensions.ENABLE_FOR_EACH) return;
		assertNames(
		'''
		webtest example.ExampleTest

		foreach a in input "a"
			element b = a
			print a,b
		end
		
		foreach a in tr ""
			element c = a
			print a,c
		end
		''',
		#{
		0 -> #[0, 1],
		1 -> #[2],
		2 -> #[3, 4],
		3 -> #[5]
		});
	}	

	@Test
	def void testForEachErrors() {
		if (!WebTestExtensions.ENABLE_FOR_EACH) return;
		assertNames(
		'''
		webtest example.ExampleTest

		operation op1(string x)
			foreach x in input "x"
				print x
			end
		end

		integer a = 7

		foreach a in input "a"
			element b = a
			print a,b
		end

		foreach b in input "b"
			foreach b in div "q"
				print b
			end
		end
		''',
		#{
		0 -> #[],
		1 -> #[0],
		2 -> #[],
		3 -> #[1,2],
		4 -> #[3],
		5 -> #[],
		6 -> #[4]
		},
		#[
		"Variable 'x' is already defined.",
		"Variable 'a' is already defined.",
		"Variable 'b' is already defined."
		]);
	}
		
	@Test
	def void testJavaScript() {
		if (!WebTestExtensions.ENABLE_JAVA_SCRIPT) return;
		assertNames(
		'''
		webtest example.ExampleTest

		operation op1(string a, string b, element c)
		    javascript a using b,c
		end
		''',
		#{
		0 -> #[0],
		1 -> #[1],
		2 -> #[2]
		});
	}	
	
	@Test
	def void testManual() {
		if (!WebTestExtensions.ENABLE_MANUAL) return;
		assertNames(
		'''
		webtest example.ExampleTest

		manual m1
		  string a = "hello"
		  fill input "q" with a
		  click button "ok"
		end
		''',
		#{
		0 -> #[0]
		});
	}	

	@Test
	def void testDuplicateManual() {
		if (!WebTestExtensions.ENABLE_MANUAL) return;
		assertNames(
		'''
		webtest example.ExampleTest

		manual m1
		end

		manual m1
		end
		''',
		#{
		},
		#[
		"Manual 'm1' is already defined."
		]);
	}	
	
	@Test
	def void testTestParams() {
		if (!WebTestExtensions.ENABLE_TEST_PARAMS) return;
		assertNames(
		'''
		webtest example.ExampleTest

		test t1(element a, string b, integer c, boolean d)
		with input "q", "hello", 5, true
		  fill a with b
		  wait c seconds until d
		end
		''',
		#{
		0 -> #[0],
		1 -> #[1],
		2 -> #[2],
		3 -> #[3]
		});
	}	
	
	@Test
	def void testDuplicateTestParameters() {
		if (!WebTestExtensions.ENABLE_TEST_PARAMS) return;
		assertNames(
		'''
		webtest example.ExampleTest

		test t1(element a, string b, integer c, boolean a)
		with input "q", "hello", 5, true
		  fill a with b
		  wait c seconds
		end
		''',
		#{
		0 -> #[0],
		1 -> #[1],
		2 -> #[2],
		3 -> #[]
		},
		#[
		"Parameter 'a' is already defined."
		]);
	}
	
	@Test
	def void testLocalHidesTestParameter() {
		if (!WebTestExtensions.ENABLE_TEST_PARAMS) return;
		assertNames(
		'''
		webtest example.ExampleTest

		test t1(element a, string b, integer c, boolean d)
		with input "q", "hello", 5, true
		  integer b = 11
		  fill a with "x"
		  wait c seconds until d
		end
		''',
		#{
		0 -> #[0],
		1 -> #[],
		2 -> #[1],
		3 -> #[2],
		4 -> #[]
		},
		#[
		"Variable 'b' is already defined."
		]);
	}
	
	@Test
	def void testInvalidTestArguments() {
		if (!WebTestExtensions.ENABLE_TEST_PARAMS) return;
		assertNames(
		'''
		webtest example.ExampleTest

		test t1(element a, string b, integer c, boolean d)
		with input "q", "hello", 5, true
		with input "q", "hello", 5
		with input "q", "hello", 5, true, 15
		  fill a with b
		  wait c seconds until d
		end
		''',
		#{
		0 -> #[0],
		1 -> #[1],
		2 -> #[2],
		3 -> #[3]
		},
		#[
		"Test case 't1' has 4 parameters but 3 arguments were specified.",
		"Test case 't1' has 4 parameters but 5 arguments were specified."
		]);
	}
	
}