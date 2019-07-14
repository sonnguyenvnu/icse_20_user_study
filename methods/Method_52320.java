private boolean isMethodCall(ASTExpression expression){
  return expression != null && expression.jjtGetNumChildren() > 0 && expression.jjtGetChild(0) instanceof ASTPrimaryExpression && expression.jjtGetChild(0).getFirstChildOfType(ASTPrimarySuffix.class) != null;
}
