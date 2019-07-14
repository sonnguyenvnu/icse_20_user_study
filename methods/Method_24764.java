public static ASTNode getParentExpression(ASTNode expression){
  if (expression instanceof SimpleName) {
    return expression;
  }
 else   if (expression instanceof FieldAccess) {
    return ((FieldAccess)expression).getExpression();
  }
 else   if (expression instanceof QualifiedName) {
    return ((QualifiedName)expression).getQualifier();
  }
 else   if (expression instanceof MethodInvocation) {
    return ((MethodInvocation)expression).getExpression();
  }
 else   if (expression instanceof ArrayAccess) {
    return ((ArrayAccess)expression).getArray();
  }
  log("getParentExpression returning NULL for " + getNodeAsString(expression));
  return null;
}
