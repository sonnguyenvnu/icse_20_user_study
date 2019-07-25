public static boolean validate(final EditorCell cell,final boolean strict,final boolean canActivatePopup){
  SubstituteInfo substituteInfo=cell.getSubstituteInfo();
  if (substituteInfo == null) {
    return false;
  }
  final SubstituteInfo substituteInfoWithPatternMatchingFilter=NodeSubstituteInfoFilterDecorator.createSubstituteInfoWithPatternMatchingFilter(substituteInfo,cell.getContext().getRepository());
  if (!(cell instanceof EditorCell_Label)) {
    return false;
  }
  final String pattern=((EditorCell_Label)cell).getText();
  if (pattern.isEmpty()) {
    return false;
  }
  List<SubstituteAction> matchingActions=new ModelAccessHelper(cell.getContext().getRepository()).runReadAction(() -> TypecheckingFacade.getFromContext().runWithSession(((EditorComponent)cell.getEditorComponent()).getTypecheckingSession(),() -> substituteInfoWithPatternMatchingFilter.getMatchingActions(pattern,strict)));
  return substituteIfPossible(cell,canActivatePopup,pattern,matchingActions);
}
