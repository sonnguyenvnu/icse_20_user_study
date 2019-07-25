protected List<Issue> validate(EObject model){
  IResourceValidator validator=((XtextResource)model.eResource()).getResourceServiceProvider().getResourceValidator();
  return validator.validate(model.eResource(),CheckMode.ALL,CancelIndicator.NullImpl);
}
