private boolean isAttributeAccess(ASTPrimaryExpression node){
  return !node.hasDescendantOfType(ASTPrimarySuffix.class);
}
