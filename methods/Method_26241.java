@Nullable private static MethodInvocationTree getSingleInvocation(StatementTree statement){
  return statement.accept(new SimpleTreeVisitor<MethodInvocationTree,Void>(){
    @Override public MethodInvocationTree visitReturn(    ReturnTree returnTree,    Void unused){
      return visit(returnTree.getExpression(),null);
    }
    @Override public MethodInvocationTree visitExpressionStatement(    ExpressionStatementTree expressionStatement,    Void unused){
      return visit(expressionStatement.getExpression(),null);
    }
    @Override public MethodInvocationTree visitMethodInvocation(    MethodInvocationTree methodInvocationTree,    Void unused){
      return methodInvocationTree;
    }
  }
,null);
}
