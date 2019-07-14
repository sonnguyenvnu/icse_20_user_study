/** 
 * Report usages of increments ('++', '--', '+=', '-=').
 * @param ignoreFlags which statements should be ignored
 */
private void checkIncrementAndDecrement(Object data,Set<String> loopVariables,ASTStatement loopBody,IgnoreFlags... ignoreFlags){
  for (  ASTPostfixExpression expression : loopBody.findDescendantsOfType(ASTPostfixExpression.class)) {
    if (ignoreNode(expression,loopBody,ignoreFlags)) {
      continue;
    }
    checkVariable(data,loopVariables,singleVariableName(expression.getFirstDescendantOfType(ASTPrimaryExpression.class)));
  }
  for (  ASTPreIncrementExpression expression : loopBody.findDescendantsOfType(ASTPreIncrementExpression.class)) {
    if (ignoreNode(expression,loopBody,ignoreFlags)) {
      continue;
    }
    checkVariable(data,loopVariables,singleVariableName(expression.getFirstDescendantOfType(ASTPrimaryExpression.class)));
  }
  for (  ASTPreDecrementExpression expression : loopBody.findDescendantsOfType(ASTPreDecrementExpression.class)) {
    if (ignoreNode(expression,loopBody,ignoreFlags)) {
      continue;
    }
    checkVariable(data,loopVariables,singleVariableName(expression.getFirstDescendantOfType(ASTPrimaryExpression.class)));
  }
  checkAssignments(data,loopVariables,loopBody,true,ignoreFlags);
}
