public boolean isStandAlonePrimitive(){
  if (jjtGetNumChildren() != 1) {
    return false;
  }
  ASTPrimaryExpression primaryExpression=getFirstChildOfType(ASTPrimaryExpression.class);
  if (primaryExpression == null || primaryExpression.jjtGetNumChildren() != 1) {
    return false;
  }
  ASTPrimaryPrefix primaryPrefix=primaryExpression.getFirstChildOfType(ASTPrimaryPrefix.class);
  if (primaryPrefix == null || primaryPrefix.jjtGetNumChildren() != 1) {
    return false;
  }
  ASTLiteral literal=primaryPrefix.getFirstChildOfType(ASTLiteral.class);
  return literal != null && !literal.isStringLiteral() && (literal.jjtGetNumChildren() == 0 || !(literal.jjtGetChild(0) instanceof ASTNullLiteral));
}
