@Override protected Optional<MatchState> matchResult(ExpressionTree tree,VisitorState state){
  MethodSymbol sym=getConstructor(tree);
  if (sym == null) {
    return Optional.empty();
  }
  return Optional.of(MatchState.create(sym.owner.type,sym));
}
