private boolean checkThreadFactoryArgument(ASTExpression expression){
  if (expression.getType() != null && ThreadFactory.class.isAssignableFrom(expression.getType())) {
    return true;
  }
  ASTName name=expression.getFirstDescendantOfType(ASTName.class);
  if (name != null && name.getType() == Executors.class) {
    return false;
  }
  ASTLambdaExpression lambdaExpression=expression.getFirstDescendantOfType(ASTLambdaExpression.class);
  if (lambdaExpression != null) {
    return isThreadFactoryLambda(lambdaExpression);
  }
 else   if (expression.getType() != null && RejectedExecutionHandler.class.isAssignableFrom(expression.getType())) {
    return false;
  }
  return true;
}
