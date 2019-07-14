@Override protected Optional<MatchState> matchResult(ExpressionTree item,MatchState info,VisitorState state){
  List<Type> actual=info.paramTypes();
  if (actual.size() != expected.size()) {
    return Optional.empty();
  }
  for (int i=0; i < actual.size(); ++i) {
    if (!ASTHelpers.isSameType(actual.get(i),expected.get(i).get(state),state)) {
      return Optional.empty();
    }
  }
  return Optional.of(info);
}
