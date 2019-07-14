private boolean isMethodType(ASTMethodDeclaration node,String methodType){
  boolean result=false;
  ASTResultType type=node.getResultType();
  if (type != null) {
    result=type.hasDescendantMatchingXPath("./Type/ReferenceType/ClassOrInterfaceType[@Image = '" + methodType + "']");
  }
  return result;
}
