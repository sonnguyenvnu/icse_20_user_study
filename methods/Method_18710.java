private static ProblemDescriptor createWarning(PsiElement element,InspectionManager manager,boolean isOnTheFly){
  return manager.createProblemDescriptor(element,"Should not be capitalized: " + element.getText(),true,ProblemHighlightType.ERROR,isOnTheFly);
}
