package webtest.dsl.tests

import org.junit.jupiter.api.Test
import webtest.dsl.WebTestExtensions

class ParseExtensionsTests extends ParseTestBase {
	
	@Test
	def void testCapture() {
		if (!WebTestExtensions.ENABLE_CAPTURE) return;
		parse(
		'''
		webtest example.CaptureTest
		
		operation op1(element e)
			capture page
			capture e
			capture button "hello"
		end
		''')
	}
	
	@Test
	def void testForEach() {
		if (!WebTestExtensions.ENABLE_FOR_EACH) return;
		parse(
		'''
		webtest example.ForEachTest
		
		operation search(string text)
		  fill textarea "q" with text
		  click input "btnK"
		  foreach result in h3 ""
		  	print result
		  end
		  element t3 = table "x"
		  foreach row in t3
		  	print row
		  end
		end
		''')
	}
	
	@Test
	def void testJavaScript() {
		if (!WebTestExtensions.ENABLE_JAVA_SCRIPT) return;
		parse(
		'''
		webtest example.JavaScriptTest
		
		operation HighlightAndClick(element elem)
		  javascript "arguments[0].style.outline = 'red solid 4px'; arguments[0].style.outlineOffset = '-4px'; arguments[0].scrollIntoView({ block: 'center', inline: 'center' });" using elem
		  wait 1 seconds
		  string disable = "arguments[0].style.outline = ''; arguments[0].style.outlineOffset = '';" 
		  javascript disable using elem
		  click elem
		end
		''')
	}
	
	@Test
	def void testManual() {
		if (!WebTestExtensions.ENABLE_MANUAL) return;
		parse(
		'''
		webtest example.ManualTest
		
		operation Click(element e)
		  //capture e
		  click e
		end
		
		operation Fill(element e, string text)
		  fill e with text
		  //capture e
		end
		
		manual LetMeGoogleThatForYou
		  print "<h1>Google search</h1>"
		  print "<p>Open the page <b>https://www.google.com</b> in a browser:</p>"
		  open "https://www.google.com"
		  //capture page
		  print "<p>Accept cookies:</p>"
		  Click using button "L2AGLb"
		  string searchText = "jwst"
		  print "<p>Type into the search field the text <b>",searchText,"</b>:</p>"
		  Fill using textarea "q", searchText
		  print "<p>Click on the <b>Google search</b> button:</p>"
		  Click using input "btnK"
		  wait 2 seconds
		  print "<p>And now you can see the search results:</p>"
		  //capture page
		end
		''')
	}
	
	@Test
	def void testTestParams() {
		if (!WebTestExtensions.ENABLE_TEST_PARAMS) return;
		parse(
		'''
		webtest example.TestParamsTest
		
		test MultiplicationTest(string left, string right, string result)
		with "2","3","6"
		with "4","7","28"
			print left, right, "=", result
		end
		''')
	}
	
}