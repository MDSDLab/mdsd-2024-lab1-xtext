package webtest.dsl.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import webtest.dsl.services.WebTestDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalWebTestDslParser extends AbstractInternalContentAssistParser {
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

    	public void setGrammarAccess(WebTestDslGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleMain"
    // InternalWebTestDsl.g:53:1: entryRuleMain : ruleMain EOF ;
    public final void entryRuleMain() throws RecognitionException {
        try {
            // InternalWebTestDsl.g:54:1: ( ruleMain EOF )
            // InternalWebTestDsl.g:55:1: ruleMain EOF
            {
             before(grammarAccess.getMainRule()); 
            pushFollow(FOLLOW_1);
            ruleMain();

            state._fsp--;

             after(grammarAccess.getMainRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMain"


    // $ANTLR start "ruleMain"
    // InternalWebTestDsl.g:62:1: ruleMain : ( ( rule__Main__Group__0 ) ) ;
    public final void ruleMain() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:66:2: ( ( ( rule__Main__Group__0 ) ) )
            // InternalWebTestDsl.g:67:2: ( ( rule__Main__Group__0 ) )
            {
            // InternalWebTestDsl.g:67:2: ( ( rule__Main__Group__0 ) )
            // InternalWebTestDsl.g:68:3: ( rule__Main__Group__0 )
            {
             before(grammarAccess.getMainAccess().getGroup()); 
            // InternalWebTestDsl.g:69:3: ( rule__Main__Group__0 )
            // InternalWebTestDsl.g:69:4: rule__Main__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Main__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMainAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMain"


    // $ANTLR start "rule__Main__Group__0"
    // InternalWebTestDsl.g:77:1: rule__Main__Group__0 : rule__Main__Group__0__Impl rule__Main__Group__1 ;
    public final void rule__Main__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:81:1: ( rule__Main__Group__0__Impl rule__Main__Group__1 )
            // InternalWebTestDsl.g:82:2: rule__Main__Group__0__Impl rule__Main__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Main__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Main__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group__0"


    // $ANTLR start "rule__Main__Group__0__Impl"
    // InternalWebTestDsl.g:89:1: rule__Main__Group__0__Impl : ( 'webtest' ) ;
    public final void rule__Main__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:93:1: ( ( 'webtest' ) )
            // InternalWebTestDsl.g:94:1: ( 'webtest' )
            {
            // InternalWebTestDsl.g:94:1: ( 'webtest' )
            // InternalWebTestDsl.g:95:2: 'webtest'
            {
             before(grammarAccess.getMainAccess().getWebtestKeyword_0()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getMainAccess().getWebtestKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group__0__Impl"


    // $ANTLR start "rule__Main__Group__1"
    // InternalWebTestDsl.g:104:1: rule__Main__Group__1 : rule__Main__Group__1__Impl rule__Main__Group__2 ;
    public final void rule__Main__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:108:1: ( rule__Main__Group__1__Impl rule__Main__Group__2 )
            // InternalWebTestDsl.g:109:2: rule__Main__Group__1__Impl rule__Main__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Main__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Main__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group__1"


    // $ANTLR start "rule__Main__Group__1__Impl"
    // InternalWebTestDsl.g:116:1: rule__Main__Group__1__Impl : ( ( rule__Main__TestClassAssignment_1 ) ) ;
    public final void rule__Main__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:120:1: ( ( ( rule__Main__TestClassAssignment_1 ) ) )
            // InternalWebTestDsl.g:121:1: ( ( rule__Main__TestClassAssignment_1 ) )
            {
            // InternalWebTestDsl.g:121:1: ( ( rule__Main__TestClassAssignment_1 ) )
            // InternalWebTestDsl.g:122:2: ( rule__Main__TestClassAssignment_1 )
            {
             before(grammarAccess.getMainAccess().getTestClassAssignment_1()); 
            // InternalWebTestDsl.g:123:2: ( rule__Main__TestClassAssignment_1 )
            // InternalWebTestDsl.g:123:3: rule__Main__TestClassAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Main__TestClassAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMainAccess().getTestClassAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group__1__Impl"


    // $ANTLR start "rule__Main__Group__2"
    // InternalWebTestDsl.g:131:1: rule__Main__Group__2 : rule__Main__Group__2__Impl ;
    public final void rule__Main__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:135:1: ( rule__Main__Group__2__Impl )
            // InternalWebTestDsl.g:136:2: rule__Main__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Main__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group__2"


    // $ANTLR start "rule__Main__Group__2__Impl"
    // InternalWebTestDsl.g:142:1: rule__Main__Group__2__Impl : ( ( rule__Main__Group_2__0 )* ) ;
    public final void rule__Main__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:146:1: ( ( ( rule__Main__Group_2__0 )* ) )
            // InternalWebTestDsl.g:147:1: ( ( rule__Main__Group_2__0 )* )
            {
            // InternalWebTestDsl.g:147:1: ( ( rule__Main__Group_2__0 )* )
            // InternalWebTestDsl.g:148:2: ( rule__Main__Group_2__0 )*
            {
             before(grammarAccess.getMainAccess().getGroup_2()); 
            // InternalWebTestDsl.g:149:2: ( rule__Main__Group_2__0 )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalWebTestDsl.g:149:3: rule__Main__Group_2__0
            	    {
            	    pushFollow(FOLLOW_5);
            	    rule__Main__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getMainAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group__2__Impl"


    // $ANTLR start "rule__Main__Group_2__0"
    // InternalWebTestDsl.g:158:1: rule__Main__Group_2__0 : rule__Main__Group_2__0__Impl rule__Main__Group_2__1 ;
    public final void rule__Main__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:162:1: ( rule__Main__Group_2__0__Impl rule__Main__Group_2__1 )
            // InternalWebTestDsl.g:163:2: rule__Main__Group_2__0__Impl rule__Main__Group_2__1
            {
            pushFollow(FOLLOW_3);
            rule__Main__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Main__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group_2__0"


    // $ANTLR start "rule__Main__Group_2__0__Impl"
    // InternalWebTestDsl.g:170:1: rule__Main__Group_2__0__Impl : ( '.' ) ;
    public final void rule__Main__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:174:1: ( ( '.' ) )
            // InternalWebTestDsl.g:175:1: ( '.' )
            {
            // InternalWebTestDsl.g:175:1: ( '.' )
            // InternalWebTestDsl.g:176:2: '.'
            {
             before(grammarAccess.getMainAccess().getFullStopKeyword_2_0()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getMainAccess().getFullStopKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group_2__0__Impl"


    // $ANTLR start "rule__Main__Group_2__1"
    // InternalWebTestDsl.g:185:1: rule__Main__Group_2__1 : rule__Main__Group_2__1__Impl ;
    public final void rule__Main__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:189:1: ( rule__Main__Group_2__1__Impl )
            // InternalWebTestDsl.g:190:2: rule__Main__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Main__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group_2__1"


    // $ANTLR start "rule__Main__Group_2__1__Impl"
    // InternalWebTestDsl.g:196:1: rule__Main__Group_2__1__Impl : ( ( rule__Main__TestClassAssignment_2_1 ) ) ;
    public final void rule__Main__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:200:1: ( ( ( rule__Main__TestClassAssignment_2_1 ) ) )
            // InternalWebTestDsl.g:201:1: ( ( rule__Main__TestClassAssignment_2_1 ) )
            {
            // InternalWebTestDsl.g:201:1: ( ( rule__Main__TestClassAssignment_2_1 ) )
            // InternalWebTestDsl.g:202:2: ( rule__Main__TestClassAssignment_2_1 )
            {
             before(grammarAccess.getMainAccess().getTestClassAssignment_2_1()); 
            // InternalWebTestDsl.g:203:2: ( rule__Main__TestClassAssignment_2_1 )
            // InternalWebTestDsl.g:203:3: rule__Main__TestClassAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Main__TestClassAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getMainAccess().getTestClassAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__Group_2__1__Impl"


    // $ANTLR start "rule__Main__TestClassAssignment_1"
    // InternalWebTestDsl.g:212:1: rule__Main__TestClassAssignment_1 : ( RULE_ID ) ;
    public final void rule__Main__TestClassAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:216:1: ( ( RULE_ID ) )
            // InternalWebTestDsl.g:217:2: ( RULE_ID )
            {
            // InternalWebTestDsl.g:217:2: ( RULE_ID )
            // InternalWebTestDsl.g:218:3: RULE_ID
            {
             before(grammarAccess.getMainAccess().getTestClassIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getMainAccess().getTestClassIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__TestClassAssignment_1"


    // $ANTLR start "rule__Main__TestClassAssignment_2_1"
    // InternalWebTestDsl.g:227:1: rule__Main__TestClassAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__Main__TestClassAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalWebTestDsl.g:231:1: ( ( RULE_ID ) )
            // InternalWebTestDsl.g:232:2: ( RULE_ID )
            {
            // InternalWebTestDsl.g:232:2: ( RULE_ID )
            // InternalWebTestDsl.g:233:3: RULE_ID
            {
             before(grammarAccess.getMainAccess().getTestClassIDTerminalRuleCall_2_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getMainAccess().getTestClassIDTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Main__TestClassAssignment_2_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001002L});

}