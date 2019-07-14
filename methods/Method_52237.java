/** 
 * That's a new method. We are going to check each method call inside the method.
 * @return <code>null</code>.
 */
@Override public Object visit(ASTMethodDeclaration node,Object data){
  List<ASTPrimaryExpression> primaryExpressions=node.findDescendantsOfType(ASTPrimaryExpression.class);
  for (  ASTPrimaryExpression expression : primaryExpressions) {
    List<MethodCall> calls=MethodCall.createMethodCalls(expression);
    addViolations(calls,(RuleContext)data);
  }
  return null;
}
