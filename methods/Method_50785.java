private void processInlineMethodCalls(ASTMethodCallExpression methodNode,Object data,final boolean isNested){
  ASTMethodCallExpression nestedCall=methodNode.getFirstChildOfType(ASTMethodCallExpression.class);
  if (nestedCall != null) {
    if (!isEscapingMethod(methodNode)) {
      processInlineMethodCalls(nestedCall,data,true);
    }
  }
  if (Helper.isMethodCallChain(methodNode,URL_PARAMETER_METHOD)) {
    if (isNested) {
      addViolation(data,methodNode);
    }
  }
}
