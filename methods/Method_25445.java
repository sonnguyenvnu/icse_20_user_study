@Override protected Optional<MatchState> matchResult(ExpressionTree item,MatchState method,VisitorState state){
  if (!method.sym().isStatic()) {
    return Optional.empty();
  }
  return Optional.of(method);
}
