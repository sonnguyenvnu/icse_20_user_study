private boolean isInitCauseCalled(String target,List<NameOccurrence> occurrences){
  boolean initCauseCalled=false;
  for (  NameOccurrence occurrence : occurrences) {
    String image=null;
    if (occurrence.getLocation() != null) {
      image=occurrence.getLocation().getImage();
    }
    if (image != null && image.endsWith("initCause")) {
      ASTPrimaryExpression primaryExpression=occurrence.getLocation().getFirstParentOfType(ASTPrimaryExpression.class);
      if (primaryExpression != null) {
        ASTArgumentList args2=primaryExpression.getFirstDescendantOfType(ASTArgumentList.class);
        if (checkForTargetUsage(target,args2)) {
          initCauseCalled=true;
          break;
        }
      }
    }
  }
  return initCauseCalled;
}
