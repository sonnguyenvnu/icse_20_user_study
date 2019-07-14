private boolean isForeignAttributeOrMethod(ASTPrimaryExpression node){
  boolean result;
  String nameImage=getNameImage(node);
  if (nameImage != null && (!nameImage.contains(".") || nameImage.startsWith("this."))) {
    result=false;
  }
 else   if (nameImage == null && node.getFirstDescendantOfType(ASTPrimaryPrefix.class).usesThisModifier()) {
    result=false;
  }
 else   if (nameImage == null && node.hasDescendantOfAnyType(ASTLiteral.class,ASTAllocationExpression.class)) {
    result=false;
  }
 else {
    result=true;
  }
  return result;
}
