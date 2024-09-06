package webtest.dsl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Inject;
import com.google.inject.Injector;

import webtest.model.Main;
import webtest.model.ModelPackage;

public class WebTestParser {
    private final Logger logger = Logger.getLogger(WebTestParser.class);

    private ModelPackage _modelPackage;
    private CharSequence _webTestCode;
    private Injector _injector;
    private List<Issue> _issues;
    private boolean _hasAnyErrors;
    private Issue _firstError;
    private Main _model;

    @Inject
    private IResourceValidator _resourceValidator;

    @Inject
    private XtextResourceSet _resourceSet;

    public WebTestParser(CharSequence webTestCode) {
    	_modelPackage = ModelPackage.eINSTANCE;
        var setup = new WebTestDslStandaloneSetup();
        _injector = setup.createInjectorAndDoEMFRegistration();
        _injector.injectMembers(this);
        _resourceSet = new XtextResourceSet();
        _resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
        _webTestCode = webTestCode;
    }

    public ModelPackage getModelPackage() {
    	return _modelPackage; 
    }
    
    public XtextResourceSet getResourceSet() { 
    	return _resourceSet; 
    }

    public boolean hasAnyErrors() { 
        if (_issues == null) parseSource();
    	return _hasAnyErrors; 
    }

    public Issue getFirstError() { 
        if (_issues == null) parseSource();
    	return _firstError; 
    }

    public List<Issue> getIssues() { 
        if (_issues == null) parseSource();
    	return _issues;
    }

    public Main getModel() {
        if (_model == null) parseSource();
        return _model;
    }

    private void parseSource() {
    	if (_issues != null) return;
        var resourceName = "dummy:/source.wt";
        logger.info("Adding resource: "+resourceName);
        var resource = _resourceSet.createResource(URI.createURI(resourceName));
        try {
			resource.load(new StringInputStream(_webTestCode.toString()), _resourceSet.getLoadOptions());
	        checkResource(resource);
	        var contents = resource.getContents();
	        if (contents != null && contents.size() == 1 && (contents.get(0) instanceof Main)) {
	            _model = (Main)contents.get(0);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void checkResource(Resource resource) {
        var issues = _resourceValidator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        _issues = new ArrayList<Issue>(issues);
        for (Issue issue : issues) {
            var logEntry = issue.getMessage()+" ["+issue.getUriToProblem()+" ("+issue.getLineNumber()+","+issue.getColumn()+")]";
            switch (issue.getSeverity()) {
                case ERROR:
                    _hasAnyErrors = true;
                    _firstError = issue;
                    logger.error(logEntry);
                    break;
                case WARNING:
                    logger.warn(logEntry);
                    break;
                case INFO:
                    logger.info(logEntry);
                    break;
                default:
                    logger.trace(logEntry);
                    break;
            }
        }
    }

}