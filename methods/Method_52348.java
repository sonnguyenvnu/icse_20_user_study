private void removeThrowableParam(final List<ASTExpression> params){
  if (params.isEmpty()) {
    return;
  }
  int lastIndex=params.size() - 1;
  ASTPrimaryExpression last=params.get(lastIndex).getFirstDescendantOfType(ASTPrimaryExpression.class);
  if (isNewThrowable(last) || hasTypeThrowable(last) || isReferencingThrowable(last)) {
    params.remove(lastIndex);
  }
}
