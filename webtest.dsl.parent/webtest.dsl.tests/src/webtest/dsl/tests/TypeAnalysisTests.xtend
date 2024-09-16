package webtest.dsl.tests

import org.junit.jupiter.api.Test
import webtest.model.Type

class TypeAnalysisTests extends TypeAnalysisTestBase {
	@Test
	def void testExpectInteger() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		integer a = 5
		wait a seconds
		''',
		#[
		]);
	}

	@Test
	def void testExpectIntegerError() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		string a = "hello"
		wait a seconds
		''',
		#[
		"Expression of type INTEGER expected but STRING was found." 
		]);
	}

	@Test
	def void testExpectString() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		string a = "Alice"
		fill input "user" with a
		''',
		#[
		]);
	}

	@Test
	def void testExpectStringError() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		integer a = 5
		fill input "user" with a
		''',
		#[
		"Expression of type STRING expected but INTEGER was found." 
		]);
	}

	@Test
	def void testExpectBoolean() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		boolean a = true
		if a then
		end
		if input "user" exists then
		end
		if not input "user" exists then
		end
		
		boolean b = false
		while b do
		end
		while input "user" exists do
		end
		while not input "user" exists do
		end
		''',
		#[ 
		]);
	}

	@Test
	def void testExpectBooleanError() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		string a = "hello"
		if a then
		end
		
		integer b = 5
		while b do
		end
		''',
		#[ 
		"Expression of type BOOLEAN expected but STRING was found.",
		"Expression of type BOOLEAN expected but INTEGER was found."
		]);
	}

	@Test
	def void testExpectElement() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		element a = input "user"
		click a
		fill a with "Alice"
		''',
		#[
		]);
	}	

	@Test
	def void testExpectElementError() {
		assertErrors(
		'''
		webtest example.ExampleTest
		
		string a = "user"
		click a
		fill a with "Alice"
		''',
		#[
		"Expression of type ELEMENT expected but STRING was found.", 
		"Expression of type ELEMENT expected but STRING was found." 
		]);
	}
	
	@Test
	def void testExpectAny() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op3(string c, boolean b)
		    print c, b
		end		
		''',
		#{
		"c" -> Type.STRING,
		"b" -> Type.BOOLEAN
		},
		#{
		"c" -> Type.ANY,
		"b" -> Type.ANY
		});
	}	
	
	@Test
	def void testIsExpression() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(element a, string b)
			boolean c = a is b
		end		
		''',
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING
		},
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING
		});
	}	
	
	@Test
	def void testContainsExpression() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(element a, string b)
			boolean c = a contains b
		end		
		''',
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING
		},
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING
		});
	}	
	
	@Test
	def void testExistsExpression() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(element a)
			boolean c = a exists
		end		
		''',
		#{
		"a" -> Type.ELEMENT
		},
		#{
		"a" -> Type.ELEMENT
		});
	}	
	
	@Test
	def void testNotExpression() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(boolean a)
			boolean c = not a
		end		
		''',
		#{
		"a" -> Type.BOOLEAN
		},
		#{
		"a" -> Type.BOOLEAN
		});
	}	
		
	@Test
	def void testNotExistsExpression() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(element e)
			boolean c = not e exists
		end		
		''',
		#{
		"e" -> Type.ELEMENT
		},
		#{
		"e" -> Type.ELEMENT
		});
	}	
	
	@Test
	def void testConstantExpression() {
		assertTypes(
		'''
		webtest example.ExampleTest

		string a = "hello"
		integer b = 5
		boolean c = true
		element d = input "q"
		string e = a
		integer f = b
		boolean g = c
		element h = d 
		''',
		#{
		"a" -> Type.STRING,
		"b" -> Type.INTEGER,
		"c" -> Type.BOOLEAN,
		"d" -> Type.ELEMENT
		},
		#{
		"a" -> Type.STRING,
		"b" -> Type.INTEGER,
		"c" -> Type.BOOLEAN,
		"d" -> Type.ELEMENT
		});
	}	
	
	@Test
	def void testIfStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(boolean a)
			if a then
			else
			end
		end		
		''',
		#{
		"a" -> Type.BOOLEAN
		},
		#{
		"a" -> Type.BOOLEAN
		});
	}	
	
	@Test
	def void testWhileStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(boolean a)
			while a do
			end
		end		
		''',
		#{
		"a" -> Type.BOOLEAN
		},
		#{
		"a" -> Type.BOOLEAN
		});
	}	
	
	@Test
	def void testCallStatementBackward() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(boolean a, element b, string c, integer d)
			if a then
			    fill b with c
			else
			    wait d seconds
			end
		end
		
		operation op2(boolean e, element f, string g, integer h)
		    op1 using e,f,g,h
		end	
		''',
		#{
		"a" -> Type.BOOLEAN,
		"b" -> Type.ELEMENT,
		"c" -> Type.STRING,
		"d" -> Type.INTEGER,
		"e" -> Type.BOOLEAN,
		"f" -> Type.ELEMENT,
		"g" -> Type.STRING,
		"h" -> Type.INTEGER
		},
		#{
		"a" -> Type.BOOLEAN,
		"b" -> Type.ELEMENT,
		"c" -> Type.STRING,
		"d" -> Type.INTEGER,
		"e" -> Type.BOOLEAN,
		"f" -> Type.ELEMENT,
		"g" -> Type.STRING,
		"h" -> Type.INTEGER
		});
	}
	
	@Test
	def void testCallStatementForward() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op2(boolean e, element f, string g, integer h)
		    op1 using e,f,g,h
		end

		operation op1(boolean a, element b, string c, integer d)
			if a then
			    fill b with c
			else
			    wait d seconds
			end
		end
		''',
		#{
		"a" -> Type.BOOLEAN,
		"b" -> Type.ELEMENT,
		"c" -> Type.STRING,
		"d" -> Type.INTEGER,
		"e" -> Type.BOOLEAN,
		"f" -> Type.ELEMENT,
		"g" -> Type.STRING,
		"h" -> Type.INTEGER
		},
		#{
		"a" -> Type.BOOLEAN,
		"b" -> Type.ELEMENT,
		"c" -> Type.STRING,
		"d" -> Type.INTEGER,
		"e" -> Type.BOOLEAN,
		"f" -> Type.ELEMENT,
		"g" -> Type.STRING,
		"h" -> Type.INTEGER
		});
	}
	
	@Test
	def void testOpenStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(string a)
			open a
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
	def void testFillStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(element a, string b)
			fill a with b
		end		
		''',
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING
		},
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.STRING
		});
	}	

	@Test
	def void testClickStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(element a)
			click a
		end		
		''',
		#{
		"a" -> Type.ELEMENT
		},
		#{
		"a" -> Type.ELEMENT
		});
	}	
	
	@Test
	def void testContextStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		page X
		end

		operation op1(element a)
			context a as X
			end
		end		

		operation op2(element b)
			context b
			end
		end		
		''',
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.ELEMENT
		},
		#{
		"a" -> Type.ELEMENT,
		"b" -> Type.ELEMENT
		});
	}

	@Test
	def void testPrintStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(string a, integer b, element c, boolean d)
			print a, b, c, d
		end		
		''',
		#{
		"a" -> Type.STRING,
		"b" -> Type.INTEGER,
		"c" -> Type.ELEMENT,
		"d" -> Type.BOOLEAN
		},
		#{
		"a" -> Type.ANY,
		"b" -> Type.ANY,
		"c" -> Type.ANY,
		"d" -> Type.ANY
		});
	}	

	@Test
	def void testAssertStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(boolean a)
			assert a
		end		
		''',
		#{
		"a" -> Type.BOOLEAN
		},
		#{
		"a" -> Type.BOOLEAN
		});
	}	

	@Test
	def void testWaitStatement() {
		assertTypes(
		'''
		webtest example.ExampleTest

		operation op1(integer a, boolean b)
			wait a seconds until b
		end		

		operation op2(integer c)
			wait c seconds
		end		

		operation op3(boolean d)
			wait until d
		end		
		''',
		#{
		"a" -> Type.INTEGER,
		"b" -> Type.BOOLEAN,
		"c" -> Type.INTEGER,
		"d" -> Type.BOOLEAN
		},
		#{
		"a" -> Type.INTEGER,
		"b" -> Type.BOOLEAN,
		"c" -> Type.INTEGER,
		"d" -> Type.BOOLEAN
		});
	}	
	
}