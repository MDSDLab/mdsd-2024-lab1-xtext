/*
 * generated by Xtext 2.35.0
 */
package webtest.dsl.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.testing.IInjectorProvider;
import webtest.dsl.ui.internal.DslActivator;

public class WebTestDslUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return DslActivator.getInstance().getInjector("webtest.dsl.WebTestDsl");
	}

}
