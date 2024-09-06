package webtest.dsl.tests

import org.eclipse.xtext.EcoreUtil2
import org.junit.jupiter.api.Test
import webtest.dsl.WebTestExtensions
import webtest.model.PrintStatement
import webtest.model.Type
import webtest.model.Variable
import webtest.model.VariableExpression

import static org.junit.jupiter.api.Assertions.*

class NameAnalysisTests extends NameAnalysisTestBase {

	@Test
	def void testFlatContext() {
		assertNames(
		'''
		webtest example.ExampleTest
		
		page A
		    element a = input "q"
		    string b = "hello"
		    integer c = 5
		    boolean d = true
		    
		    operation op1(element x)
		        click x
		    end
		end
		
		context as A
		    element Aa = a
		    string Ab = b
		    integer Ac = c
		    boolean Ad = d
		    op1 using button "ok"
		end
		''',
		#{
		0 -> #[1],
		1 -> #[2],
		2 -> #[3],
		3 -> #[4],
		4 -> #[0],
		5 -> #[],
		6 -> #[],
		7 -> #[],
		8 -> #[]
		});
	}

	@Test
	def void testHierarchicalContext() {
		assertNames(
		'''
		webtest example.ExampleTest
		
		page A
		    element a = input "q"
		    string b = "hello"
		    integer c = 5
		    boolean d = true
		    
		    operation op1(element x)
		        click x
		    end
		end
		
		page B
		    string a = "b"
		    integer b = 11
		    
		    operation op1(integer y)
		        wait y seconds
		    end
		end
		
		page C
		    integer a = 7
		    
		    operation op1(boolean z)
		        wait until z
		    end
		end
		
		operation op1(string w)
			fill input "q" with w
		end
		
		operation op0(element p, integer q, boolean r, string s)
			context as A
			    element Aa = a
			    string Ab = b
			    integer Ac = c
			    boolean Ad = d
			    op1 using p
			    context as B
			        string Ba = a
			        integer Bb = b
			        integer Bc = c
			        boolean Bd = d
			        op1 using q
			        context as C
				        integer Ca = a
				        integer Cb = b
				        integer Cc = c
				        boolean Cd = d
				        op1 using r
			        end
			    end
			end
			op1 using s
		end
		''',
		#{
		0 -> #[4],
		1 -> #[5],
		2 -> #[6, 11, 16],
		3 -> #[7, 12, 17],
		4 -> #[0],
		5 -> #[9],
		6 -> #[10, 15],
		7 -> #[1],
		8 -> #[14],
		9 -> #[2],
		10 -> #[3],
		11 -> #[8],
		12 -> #[13],
		13 -> #[18],
		14 -> #[19],
		15 -> #[],
		16 -> #[],
		17 -> #[],
		18 -> #[],
		19 -> #[],
		20 -> #[],
		21 -> #[],
		22 -> #[],
		23 -> #[],
		24 -> #[],
		25 -> #[],
		26 -> #[]
		});
	}
	
	@Test
	def void testLocalVariables() {
		assertNames(
		'''
		webtest example.ExampleTest
		
		operation op1(string m)
		    integer a = 5
		    integer b = a
		    string c = m
		end

		integer x = 5
		integer y = x
		''',
		#{
		0 -> #[1],
		1 -> #[0],
		2 -> #[],
		3 -> #[],
		4 -> #[2],
		5 -> #[]	
		});
	}	

	@Test
	def void testContextVariables() {
		assertNames(
		'''
		webtest example.ExampleTest
		
		operation op1(element e1)
			context e1
				element e2 = div "e2"
				context e2
					print e1, e2
				end
			end
		end

		element e1 = div "e1"
		context e1
			element e2 = div "e2"
			context e2
				print e1, e2
			end
		end
		''',
		#{
		0 -> #[0,2],
		1 -> #[1,3],
		2 -> #[4,6],
		3 -> #[5,7]
		});
	}
	
	
	@Test
	def void testLocalVariableErrors() {
		assertNames(
		'''
		webtest example.ExampleTest
		
		operation op1(string m)
		    string a = b
		    string b = c
		    string c = m
		end

		string x = y
		string y = z
		string z = "zzz"
		
		boolean w = true
		if w then
		    integer w = 5
		    integer u = 7
		else
		    string w = "w"
		end
		integer v = u
		''',
		#{
		0 -> #[2],
		1 -> #[],
		2 -> #[],
		3 -> #[],
		4 -> #[],
		5 -> #[],
		6 -> #[],
		7 -> #[5],
		8 -> #[],
		9 -> #[],
		10 -> #[],
		11 -> #[]
		},
		#[
		"Couldn't resolve reference to Variable 'b'.",
		"Couldn't resolve reference to Variable 'c'.",
		"Couldn't resolve reference to Variable 'y'.",
		"Couldn't resolve reference to Variable 'z'.",
		"Couldn't resolve reference to Variable 'u'.",
		"Variable 'w' is already defined.",
		"Variable 'w' is already defined.",
		// The following error messages will come from the type analysis:
		"Expression of type STRING expected but UNDEFINED was found.",
		"Expression of type STRING expected but UNDEFINED was found.",
		"Expression of type STRING expected but UNDEFINED was found.",
		"Expression of type STRING expected but UNDEFINED was found.",
		"Expression of type INTEGER expected but UNDEFINED was found."
		]);
	}

	@Test
	def void testDuplicateTests() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		test t1
		end
		
		test t1
		end
		''',
		#[
		"Test case 't1' is already defined."
		]);
	}	
	
	@Test
	def void testDuplicateVariables() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		operation op1
			integer a = 5
			integer a = 10
		end
		
		boolean b = true
		string b = "hello"
		''',
		#[
		"Variable 'a' is already defined.",
		"Variable 'b' is already defined."
		]);
	}	
	
	@Test
	def void testDuplicateOperations() {
		assertErrors(
		'''
		webtest example.ExampleTest

		page A
			operation op1(string a)
				print a
			end
			
			operation op1
			end
			
			operation op1(integer x, integer y)
				print x,y
			end
		end

		operation op1(boolean b)
			print b
		end
		
		operation op1
		end
		
		operation op1(element q, element r)
			print q,r
		end
		''',
		#[
		"Operation 'op1' is already defined.",
		"Operation 'op1' is already defined.",
		"Operation 'op1' is already defined.",
		"Operation 'op1' is already defined."
		]);
	}	
	
	@Test
	def void testDuplicateParameters() {
		assertErrors(
		'''
		webtest example.ExampleTest

		page A
			operation op1(string a, string b, integer a)
				print a,b
			end
		end

		operation op1(string x, boolean y, integer y)
			print x,y
		end
		''',
		#[
		"Parameter 'a' is already defined.",
		"Parameter 'y' is already defined."
		]);
	}
	
	@Test
	def void testLocalHidesParameter() {
		assertErrors(
		'''
		webtest example.ExampleTest

		page A
			operation op1(string a, boolean b)
				integer a = 5
				print a,b
			end
		end

		operation op1(string x, string y)
			string y = "hello"
			print x,y
		end
		''',
		#[
		"Variable 'a' is already defined.",
		"Variable 'y' is already defined."
		]);
	}
	
	@Test
	def void testInvalidArguments() {
		assertErrors(
		'''
		webtest example.ExampleTest

		page A
			operation op1(string a, string b, string c)
				print a,b,c
			end
		end

		operation op1(string x, string y)
			print x,y
		end
		
		operation op2
			op1 using "a"
			op1 using "a", "b", "c"
		end
		''',
		#[
		"Operation 'op1' has 2 parameters but 1 arguments were specified.",
		"Operation 'op1' has 2 parameters but 3 arguments were specified."
		]);
	}

}