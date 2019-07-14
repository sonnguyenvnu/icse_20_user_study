private void findAuthLiterals(final AbstractApexNode<?> node){
  ASTLiteralExpression literal=node.getFirstChildOfType(ASTLiteralExpression.class);
  if (literal != null) {
    ASTVariableExpression variable=node.getFirstChildOfType(ASTVariableExpression.class);
    if (variable != null) {
      if (isAuthorizationLiteral(literal)) {
        listOfAuthorizationVariables.add(Helper.getFQVariableName(variable));
      }
    }
  }
}
