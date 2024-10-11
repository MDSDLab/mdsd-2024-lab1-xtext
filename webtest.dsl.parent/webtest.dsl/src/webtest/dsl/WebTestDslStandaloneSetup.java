/*
 * generated by Xtext 2.35.0
 */
package webtest.dsl;

import org.eclipse.emf.ecore.EPackage;

import com.google.inject.Injector;

import webtest.model.ModelPackage;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class WebTestDslStandaloneSetup extends WebTestDslStandaloneSetupGenerated {

	public static void doSetup() {
		new WebTestDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
	
	@Override
	public void register(Injector injector) {
		if (!EPackage.Registry.INSTANCE.containsKey(ModelPackage.eNS_URI)) {
            EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
        }
		super.register(injector);
	}
}
