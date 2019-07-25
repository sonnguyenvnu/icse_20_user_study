public List<ErrorReportingItem> check(){
  List<ErrorReportingItem> errors=checkHierarchyProblems();
  checkNotImplementedAbstractMethods(errors);
  checkCorrespondenceToBaseMethodProblems(errors);
  return errors;
}
