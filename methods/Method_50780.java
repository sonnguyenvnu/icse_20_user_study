private void runChecks(final AbstractApexNode<?> node,Object data){
  ASTLiteralExpression literalNode=node.getFirstChildOfType(ASTLiteralExpression.class);
  if (literalNode != null) {
    if (isAuthorizationLiteral(literalNode)) {
      addViolation(data,literalNode);
    }
  }
  final ASTVariableExpression varNode=node.getFirstChildOfType(ASTVariableExpression.class);
  if (varNode != null) {
    if (listOfAuthorizationVariables.contains(Helper.getFQVariableName(varNode))) {
      addViolation(data,varNode);
    }
  }
}
