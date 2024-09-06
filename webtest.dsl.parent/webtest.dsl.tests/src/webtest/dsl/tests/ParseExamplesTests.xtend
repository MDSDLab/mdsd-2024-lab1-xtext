package webtest.dsl.tests

import org.junit.jupiter.api.Test

class ParseExamplesTests extends ParseTestBase {

	@Test
	def void testHelloWorld() {
		parse(
		'''
		webtest example.HelloWorldTest
		
		test SayHelloTest
			print "Hello World!"
		end
		''')
	}

	@Test
	def void testGoogle() {
		parse(
		'''
		webtest example.GoogleTest
		
		open "https://www.google.com"
		
		element acceptCookies = button "L2AGLb"
		if acceptCookies exists then
		    click acceptCookies
		end
		
		fill textarea "q" with "jwst"
		click button "Search"
		wait 5 seconds
		''')
	}

	@Test
	def void testCalculator() {
		parse(
		'''
		webtest example.CalculatorTest
		
		page Calculator
		  element display = input "number display"
		  element clear = button "AC"
		  element add = button "+"
		  element subtract = button "-"
		  element multiply = button "×"
		  element divide = button "/"
		  element compute = button "="
		  
		  operation binaryOperation(string left, element op, string right)
		    click clear
		    fill display with left
		    click op
		    fill display with right
		    click compute
		  end
		  
		  operation multiply(string left, string right)
		    binaryOperation using left, multiply, right
		  end
		end
		
		test mul1
		  open "https://www.calculatorsoup.com/calculators/math/basic.php"
		  context as Calculator
		    wait until display exists
		    print "Page opened"
		    multiply using "23","6"
		    assert display is "138"
		  end
		end
		
		integer timeout = 10
		open "https://www.calculatorsoup.com/calculators/math/basic.php"
		wait timeout seconds until input "number display" exists 
		click button "AC"
		fill input "number display" with "23"
		click button "×"
		fill input "number display" with "6"
		click button "="
		assert input "number display" is "138"
		''')
	}

	@Test
	def void testDialog() {
		parse(
		'''
		webtest example.DialogTest
		
		page ModalDialog
		  element close = button "Close"
		end
		
		test largeModalTest
		  open "https://getbootstrap.com/docs/4.0/components/modal/"
		  click button "Large modal"
		  context div "myLargeModalLabel" as ModalDialog
		    click close
		  end
		end
		
		test smallModalTest
		  open "https://getbootstrap.com/docs/4.0/components/modal/"
		  click button "Small modal"
		  context div "mySmallModalLabel" as ModalDialog
		    click close
		  end
		end
		''')
	}
	
	@Test
	def void testWizard() {
		parse(
		'''
		webtest example.WizardTest
		
		page Name
		  element firstName = input "First name..."
		  element lastName = input "Last name..."
		  element next = button "Next"
		end
		
		page ContactInfo
		  element email = input "E-mail..."
		  element phone = input "Phone..."
		  element next = button "Next"
		  element previous = button "Previous"
		end
		
		page Birthday
		  element day = input "dd"
		  element month = input "mm"
		  element year = input "yyyy"
		  element next = button "Next"
		  element previous = button "Previous"
		end
		
		page LoginInfo
		  element username = input "Username..."
		  element password = input "Password..."
		  element submit = button "Submit"
		  element previous = button "Previous"
		end
		
		open "https://www.w3schools.com/howto/howto_js_form_steps.asp"
		
		context as Name
		  fill firstName with "Agent"
		  fill lastName with "Smith"
		  click next
		end
		
		context as ContactInfo
		  fill email with "smith@matrix.com"
		  fill phone with "5551234"
		  click next
		end
		
		context as Birthday
		  fill day with "01"
		  fill month with "01"
		  fill year with "2000"
		  click previous
		end
		
		context as ContactInfo
		  fill phone with "5554321"
		  click next
		end
		
		context as Birthday
		  click next
		end
		
		context as LoginInfo
		  fill username with "smith"
		  fill password with "secret"
		  click submit
		end
		''')
	}
	
}