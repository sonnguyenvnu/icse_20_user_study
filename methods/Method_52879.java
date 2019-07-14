private boolean containsSafeFields(final AbstractVFNode expression){
  final ASTExpression ex=expression.getFirstChildOfType(ASTExpression.class);
  return ex != null && innerContainsSafeFields(ex);
}
