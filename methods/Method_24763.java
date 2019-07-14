/** 
 * For a().abc.a123 this would return a123
 * @param expression
 * @return
 */
public static ASTNode getChildExpression(ASTNode expression){
  if (expression instanceof SimpleName) {
    return expression;
  }
 else   if (expression instanceof FieldAccess) {
    return ((FieldAccess)expression).getName();
  }
 else   if (expression instanceof QualifiedName) {
    return ((QualifiedName)expression).getName();
  }
 else   if (expression instanceof MethodInvocation) {
    return ((MethodInvocation)expression).getName();
  }
 else   if (expression instanceof ArrayAccess) {
    return ((ArrayAccess)expression).getArray();
  }
  log(" getChildExpression returning NULL for " + getNodeAsString(expression));
  return null;
}
