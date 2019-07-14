private void validateParameters(ASTMethodCallExpression methodCall,Object data){
  List<ASTVariableExpression> variables=methodCall.findDescendantsOfType(ASTVariableExpression.class);
  for (  ASTVariableExpression var : variables) {
    if (REGEXP.matcher(var.getImage()).matches()) {
      if (!whiteListedVariables.contains(Helper.getFQVariableName(var))) {
        addViolation(data,methodCall);
      }
    }
  }
}
