private static Optional<Symbol> returnUnarySym(StatementTree s){
  if (s instanceof ExpressionStatementTree) {
    if (((ExpressionStatementTree)s).getExpression() instanceof UnaryTree) {
      UnaryTree unaryTree=(UnaryTree)((ExpressionStatementTree)s).getExpression();
      return Optional.ofNullable(ASTHelpers.getSymbol(unaryTree.getExpression()));
    }
  }
  return Optional.empty();
}
