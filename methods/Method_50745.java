private void mapCallToMethodDecl(final AbstractApexNode<?> self,final Set<ASTMethodCallExpression> innerMethodCalls,final List<ASTMethodCallExpression> nodes){
  for (  ASTMethodCallExpression node : nodes) {
    if (Objects.equal(node,self)) {
      break;
    }
    final ASTMethod methodBody=resolveMethodCalls(node);
    if (methodBody != null) {
      innerMethodCalls.addAll(methodBody.findDescendantsOfType(ASTMethodCallExpression.class));
    }
  }
}
