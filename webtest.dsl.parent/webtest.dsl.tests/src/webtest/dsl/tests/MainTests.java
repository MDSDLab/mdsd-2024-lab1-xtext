package webtest.dsl.tests;

import java.io.PrintWriter;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class MainTests {
	private static SummaryGeneratingListener listener = new SummaryGeneratingListener();
	
	public static void main(String[] args) {
		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
          .selectors(
        		  DiscoverySelectors.selectClass(ParseTests.class),
        		  DiscoverySelectors.selectClass(ParseExtensionsTests.class),
        		  DiscoverySelectors.selectClass(ParseExamplesTests.class),
        		  DiscoverySelectors.selectClass(NameAnalysisTests.class),
        		  DiscoverySelectors.selectClass(NameAnalysisExtensionsTests.class),
        		  DiscoverySelectors.selectClass(TypeAnalysisTests.class),
        		  DiscoverySelectors.selectClass(TypeAnalysisExtensionsTests.class),
        		  DiscoverySelectors.selectClass(WebTestDslParsingTest.class))
          .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        
        TestExecutionSummary summary = listener.getSummary();
        summary.printTo(new PrintWriter(System.out));
	}
}
