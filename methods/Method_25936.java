@Override public Description matchForLoop(ForLoopTree forLoopTree,VisitorState visitorState){
  List<? extends ExpressionStatementTree> updates=forLoopTree.getUpdate();
  final Set<Symbol> incrementedSymbols=updates.stream().filter(expStateTree -> expStateTree.getExpression() instanceof UnaryTree).map(expStateTree -> ASTHelpers.getSymbol(((UnaryTree)expStateTree.getExpression()).getExpression())).collect(Collectors.toCollection(HashSet::new));
  StatementTree body=forLoopTree.getStatement();
  List<? extends StatementTree> statementTrees=body instanceof BlockTree ? ((BlockTree)body).getStatements() : ImmutableList.of(body);
  for (  StatementTree s : statementTrees) {
    if (!CONDITIONALS.contains(s.getKind())) {
      Optional<Symbol> opSymbol=returnUnarySym(s);
      if (opSymbol.isPresent() && incrementedSymbols.contains(opSymbol.get())) {
        return describeMatch(forLoopTree);
      }
    }
  }
  return Description.NO_MATCH;
}
