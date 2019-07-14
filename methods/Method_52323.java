private boolean hasNullInitializer(ASTLocalVariableDeclaration var){
  ASTVariableInitializer init=var.getFirstDescendantOfType(ASTVariableInitializer.class);
  if (init != null) {
    try {
      List<?> nulls=init.findChildNodesWithXPath("Expression/PrimaryExpression/PrimaryPrefix/Literal/NullLiteral");
      return !nulls.isEmpty();
    }
 catch (    JaxenException e) {
      return false;
    }
  }
  return false;
}
