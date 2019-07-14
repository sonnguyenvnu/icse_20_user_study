private int getAmountOfExpectedArguments(final String variableName,final List<ASTVariableDeclarator> variables){
  for (  final ASTVariableDeclarator astVariableDeclarator : variables) {
    if (astVariableDeclarator.getFirstChildOfType(ASTVariableDeclaratorId.class).getImage().equals(variableName)) {
      ASTVariableInitializer variableInitializer=astVariableDeclarator.getFirstDescendantOfType(ASTVariableInitializer.class);
      ASTExpression expression=null;
      if (variableInitializer != null) {
        expression=variableInitializer.getFirstChildOfType(ASTExpression.class);
      }
      if (expression != null) {
        return countPlaceholders(expression);
      }
    }
  }
  return 0;
}
