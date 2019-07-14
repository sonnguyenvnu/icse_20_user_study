private boolean isMethodThrowingType(ASTMethodDeclaration node,List<String> exceptedExceptions){
  boolean result=false;
  ASTNameList thrownsExceptions=node.getFirstChildOfType(ASTNameList.class);
  if (thrownsExceptions != null) {
    List<ASTName> names=thrownsExceptions.findChildrenOfType(ASTName.class);
    for (    ASTName name : names) {
      for (      String exceptedException : exceptedExceptions) {
        if (exceptedException.equals(name.getImage())) {
          result=true;
        }
      }
    }
  }
  return result;
}
