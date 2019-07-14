private int processAdditive(Node sn){
  ASTAdditiveExpression additive=sn.getFirstDescendantOfType(ASTAdditiveExpression.class);
  if (additive == null) {
    return 0;
  }
  int anticipatedLength=0;
  for (int ix=0; ix < additive.jjtGetNumChildren(); ix++) {
    Node childNode=additive.jjtGetChild(ix);
    ASTLiteral literal=childNode.getFirstDescendantOfType(ASTLiteral.class);
    if (literal != null && literal.getImage() != null) {
      anticipatedLength+=literal.getImage().length() - 2;
    }
  }
  return anticipatedLength;
}
