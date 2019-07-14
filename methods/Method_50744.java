private void recursivelyEvaluateCRUDMethodCalls(final AbstractApexNode<?> self,final Set<ASTMethodCallExpression> innerMethodCalls,final ASTBlockStatement blockStatement){
  if (blockStatement != null) {
    int numberOfStatements=blockStatement.jjtGetNumChildren();
    for (int i=0; i < numberOfStatements; i++) {
      Node n=blockStatement.jjtGetChild(i);
      if (n instanceof ASTIfElseBlockStatement) {
        List<ASTBlockStatement> innerBlocks=n.findDescendantsOfType(ASTBlockStatement.class);
        for (        ASTBlockStatement innerBlock : innerBlocks) {
          recursivelyEvaluateCRUDMethodCalls(self,innerMethodCalls,innerBlock);
        }
      }
      AbstractApexNode<?> match=n.getFirstDescendantOfType(self.getClass());
      if (Objects.equal(match,self)) {
        break;
      }
      ASTMethodCallExpression methodCall=n.getFirstDescendantOfType(ASTMethodCallExpression.class);
      if (methodCall != null) {
        mapCallToMethodDecl(self,innerMethodCalls,Arrays.asList(methodCall));
      }
    }
  }
}
