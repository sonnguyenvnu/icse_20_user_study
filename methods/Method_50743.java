private Set<ASTMethodCallExpression> getPreviousMethodCalls(final AbstractApexNode<?> self){
  final Set<ASTMethodCallExpression> innerMethodCalls=new HashSet<>();
  final ASTMethod outerMethod=self.getFirstParentOfType(ASTMethod.class);
  if (outerMethod != null) {
    final ASTBlockStatement blockStatement=outerMethod.getFirstChildOfType(ASTBlockStatement.class);
    recursivelyEvaluateCRUDMethodCalls(self,innerMethodCalls,blockStatement);
    final List<ASTMethod> constructorMethods=findConstructorlMethods();
    for (    ASTMethod method : constructorMethods) {
      innerMethodCalls.addAll(method.findDescendantsOfType(ASTMethodCallExpression.class));
    }
    mapCallToMethodDecl(self,innerMethodCalls,new ArrayList<ASTMethodCallExpression>(innerMethodCalls));
  }
  return innerMethodCalls;
}
