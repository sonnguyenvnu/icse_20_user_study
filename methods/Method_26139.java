private static boolean invokedConstructorMustBeClosed(VisitorState state,MethodTree methodTree){
  List<? extends StatementTree> statements=methodTree.getBody().getStatements();
  if (statements.isEmpty()) {
    return false;
  }
  ExpressionStatementTree est=(ExpressionStatementTree)statements.get(0);
  MethodInvocationTree mit=(MethodInvocationTree)est.getExpression();
  MethodSymbol invokedConstructorSymbol=ASTHelpers.getSymbol(mit);
  return ASTHelpers.hasAnnotation(invokedConstructorSymbol,MustBeClosed.class,state);
}
