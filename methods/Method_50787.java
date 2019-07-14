private void processEscapingMethodCalls(ASTMethodCallExpression methodNode,Object data){
  ASTMethodCallExpression nestedCall=methodNode.getFirstChildOfType(ASTMethodCallExpression.class);
  if (nestedCall != null) {
    processEscapingMethodCalls(nestedCall,data);
  }
  final ASTVariableExpression variable=methodNode.getFirstChildOfType(ASTVariableExpression.class);
  if (variable != null) {
    if (urlParameterStrings.contains(Helper.getFQVariableName(variable))) {
      if (!isEscapingMethod(methodNode)) {
        addViolation(data,variable);
      }
    }
  }
}
