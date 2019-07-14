private Description checkInvocation(Tree tree,Type methodType,VisitorState state,Symbol symbol){
  ImmutableAnalysis analysis=createImmutableAnalysis(state);
  Violation info=analysis.checkInvocation(methodType,symbol);
  if (info.isPresent()) {
    state.reportMatch(buildDescription(tree).setMessage(info.message()).build());
  }
  return NO_MATCH;
}
