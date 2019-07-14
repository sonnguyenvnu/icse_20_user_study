private boolean isThreadFactoryLambda(ASTLambdaExpression lambdaExpression){
  List<ASTVariableDeclaratorId> variableDeclaratorIds=lambdaExpression.findChildrenOfType(ASTVariableDeclaratorId.class);
  if (variableDeclaratorIds != null && !variableDeclaratorIds.isEmpty()) {
    return variableDeclaratorIds.size() == SINGLE_LENGTH;
  }
  ASTFormalParameters parameters=lambdaExpression.getFirstChildOfType(ASTFormalParameters.class);
  if (parameters == null) {
    return false;
  }
  ASTFormalParameter parameter=parameters.getFirstChildOfType(ASTFormalParameter.class);
  if (parameter == null) {
    return false;
  }
  ASTVariableDeclaratorId variableDeclaratorId=parameter.getFirstChildOfType(ASTVariableDeclaratorId.class);
  if (variableDeclaratorId == null) {
    return false;
  }
  return Runnable.class == variableDeclaratorId.getType();
}
