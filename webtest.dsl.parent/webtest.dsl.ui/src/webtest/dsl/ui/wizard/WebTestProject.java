package webtest.dsl.ui.wizard;

import java.util.ArrayList;

import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.template.AbstractProjectTemplate;
import org.eclipse.xtext.ui.wizard.template.IProjectGenerator;
import org.eclipse.xtext.ui.wizard.template.ProjectTemplate;

import webtest.generator.WebTestProjectGenerator;

@ProjectTemplate(label="Web Test", icon="project_template.png", description="<p><b>Web Test</b></p><p>This project is for testing web pages.</p>")
public class WebTestProject extends AbstractProjectTemplate {
	final static String JAVA_NATURE_ID = "org.eclipse.jdt.core.javanature";
	final static String MAVEN_NATURE_ID = "org.eclipse.m2e.core.maven2Nature";
	final static String JAVA_BUILDER_ID = "org.eclipse.jdt.core.javabuilder";
	final static String MAVEN_BUILDER_ID = "org.eclipse.m2e.core.maven2Builder";
	
	@Override
	public void generateProjects(IProjectGenerator generator) {
		var f = new ProjectFactory();
		f.setProjectName(this.getProjectInfo().getProjectName());
		f.setLocation(this.getProjectInfo().getLocationPath());
		
		f.addProjectNatures(JAVA_NATURE_ID, MAVEN_NATURE_ID, XtextProjectHelper.NATURE_ID);
		f.addBuilderIds(JAVA_BUILDER_ID, MAVEN_BUILDER_ID, XtextProjectHelper.BUILDER_ID);
		
		var folders = new ArrayList<String>();
		folders.add("src/main/java");
		folders.add("src/main/resources");
		folders.add("src/test/java");
		folders.add("src/test/resources");
		folders.add("webtest");
		f.addFolders(folders);
		
		var wtg = new WebTestProjectGenerator();
		addFile(f, "pom.xml", wtg.generatePomXml(this.getProjectInfo().getProjectName()));
		addFile(f, ".classpath", wtg.generateClasspath());
		addFile(f, ".gitignore", wtg.generateGitignore());
		addFile(f, "src/main/java/.gitignore", "");
		addFile(f, "src/main/resources/logback.xml", wtg.generateLogback());
		addFile(f, "src/test/java/.gitignore", "");
		addFile(f, "src/test/resources/logback-test.xml", wtg.generateLogbackTest());
		addFile(f, "webtest/HelloWorld.wt", wtg.generateHelloWorld());
		addFile(f, "src/test/java/webtest/selenium/api/Page.java", wtg.generatePage());
		addFile(f, "src/test/java/webtest/selenium/api/PageElement.java", wtg.generatePageElement());
		addFile(f, "src/test/java/webtest/selenium/api/SeleniumTest.java", wtg.generateSeleniumTest());
		
		generator.generate(f);
	}
}