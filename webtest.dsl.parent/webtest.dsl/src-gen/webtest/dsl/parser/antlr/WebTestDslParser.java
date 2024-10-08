/*
 * generated by Xtext 2.35.0
 */
package webtest.dsl.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import webtest.dsl.parser.antlr.internal.InternalWebTestDslParser;
import webtest.dsl.services.WebTestDslGrammarAccess;

public class WebTestDslParser extends AbstractAntlrParser {

	@Inject
	private WebTestDslGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalWebTestDslParser createParser(XtextTokenStream stream) {
		return new InternalWebTestDslParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Main";
	}

	public WebTestDslGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(WebTestDslGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
