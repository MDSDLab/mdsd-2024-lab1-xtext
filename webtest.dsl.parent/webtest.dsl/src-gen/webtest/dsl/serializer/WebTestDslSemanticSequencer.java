/*
 * generated by Xtext 2.35.0
 */
package webtest.dsl.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import webtest.dsl.services.WebTestDslGrammarAccess;
import webtest.model.Main;
import webtest.model.ModelPackage;

@SuppressWarnings("all")
public class WebTestDslSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private WebTestDslGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == ModelPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case ModelPackage.MAIN:
				sequence_Main(context, (Main) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Main returns Main
	 *
	 * Constraint:
	 *     (testClass+=ID testClass+=ID*)
	 * </pre>
	 */
	protected void sequence_Main(ISerializationContext context, Main semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
