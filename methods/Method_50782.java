private void validateLiteralPresence(ASTMethodCallExpression methodCall,Object data){
  List<ASTVariableExpression> variables=methodCall.findDescendantsOfType(ASTVariableExpression.class);
  for (  ASTVariableExpression v : variables) {
    addViolation(data,v);
  }
}
