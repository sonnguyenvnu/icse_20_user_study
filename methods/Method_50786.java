private void findTaintedVariables(AbstractApexNode<?> node,Object data){
  final ASTMethodCallExpression right=node.getFirstChildOfType(ASTMethodCallExpression.class);
  if (right != null) {
    if (Helper.isMethodCallChain(right,URL_PARAMETER_METHOD)) {
      ASTVariableExpression left=node.getFirstChildOfType(ASTVariableExpression.class);
      String varType=null;
      if (node instanceof ASTVariableDeclaration) {
        varType=((ASTVariableDeclaration)node).getType();
      }
      if (left != null) {
        if (varType == null || !"id".equalsIgnoreCase(varType)) {
          urlParameterStrings.add(Helper.getFQVariableName(left));
        }
      }
    }
    processEscapingMethodCalls(right,data);
  }
}
