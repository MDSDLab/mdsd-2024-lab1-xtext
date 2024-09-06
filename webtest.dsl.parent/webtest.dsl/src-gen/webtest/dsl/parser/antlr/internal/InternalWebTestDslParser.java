package webtest.dsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import webtest.dsl.services.WebTestDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalWebTestDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'webtest'", "'.'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int RULE_INT=5;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalWebTestDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalWebTestDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalWebTestDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalWebTestDsl.g"; }



     	private WebTestDslGrammarAccess grammarAccess;

        public InternalWebTestDslParser(TokenStream input, WebTestDslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Main";
       	}

       	@Override
       	protected WebTestDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleMain"
    // InternalWebTestDsl.g:64:1: entryRuleMain returns [EObject current=null] : iv_ruleMain= ruleMain EOF ;
    public final EObject entryRuleMain() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMain = null;


        try {
            // InternalWebTestDsl.g:64:45: (iv_ruleMain= ruleMain EOF )
            // InternalWebTestDsl.g:65:2: iv_ruleMain= ruleMain EOF
            {
             newCompositeNode(grammarAccess.getMainRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMain=ruleMain();

            state._fsp--;

             current =iv_ruleMain; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMain"


    // $ANTLR start "ruleMain"
    // InternalWebTestDsl.g:71:1: ruleMain returns [EObject current=null] : (otherlv_0= 'webtest' ( (lv_testClass_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_testClass_3_0= RULE_ID ) ) )* ) ;
    public final EObject ruleMain() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_testClass_1_0=null;
        Token otherlv_2=null;
        Token lv_testClass_3_0=null;


        	enterRule();

        try {
            // InternalWebTestDsl.g:77:2: ( (otherlv_0= 'webtest' ( (lv_testClass_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_testClass_3_0= RULE_ID ) ) )* ) )
            // InternalWebTestDsl.g:78:2: (otherlv_0= 'webtest' ( (lv_testClass_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_testClass_3_0= RULE_ID ) ) )* )
            {
            // InternalWebTestDsl.g:78:2: (otherlv_0= 'webtest' ( (lv_testClass_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_testClass_3_0= RULE_ID ) ) )* )
            // InternalWebTestDsl.g:79:3: otherlv_0= 'webtest' ( (lv_testClass_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_testClass_3_0= RULE_ID ) ) )*
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getMainAccess().getWebtestKeyword_0());
            		
            // InternalWebTestDsl.g:83:3: ( (lv_testClass_1_0= RULE_ID ) )
            // InternalWebTestDsl.g:84:4: (lv_testClass_1_0= RULE_ID )
            {
            // InternalWebTestDsl.g:84:4: (lv_testClass_1_0= RULE_ID )
            // InternalWebTestDsl.g:85:5: lv_testClass_1_0= RULE_ID
            {
            lv_testClass_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_testClass_1_0, grammarAccess.getMainAccess().getTestClassIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMainRule());
            					}
            					addWithLastConsumed(
            						current,
            						"testClass",
            						lv_testClass_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalWebTestDsl.g:101:3: (otherlv_2= '.' ( (lv_testClass_3_0= RULE_ID ) ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalWebTestDsl.g:102:4: otherlv_2= '.' ( (lv_testClass_3_0= RULE_ID ) )
            	    {
            	    otherlv_2=(Token)match(input,12,FOLLOW_3); 

            	    				newLeafNode(otherlv_2, grammarAccess.getMainAccess().getFullStopKeyword_2_0());
            	    			
            	    // InternalWebTestDsl.g:106:4: ( (lv_testClass_3_0= RULE_ID ) )
            	    // InternalWebTestDsl.g:107:5: (lv_testClass_3_0= RULE_ID )
            	    {
            	    // InternalWebTestDsl.g:107:5: (lv_testClass_3_0= RULE_ID )
            	    // InternalWebTestDsl.g:108:6: lv_testClass_3_0= RULE_ID
            	    {
            	    lv_testClass_3_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            	    						newLeafNode(lv_testClass_3_0, grammarAccess.getMainAccess().getTestClassIDTerminalRuleCall_2_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getMainRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"testClass",
            	    							lv_testClass_3_0,
            	    							"org.eclipse.xtext.common.Terminals.ID");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMain"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001002L});

}