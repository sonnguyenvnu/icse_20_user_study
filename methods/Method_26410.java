@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).setMessage(String.format("Use of %s is a no-op and is not allowed.",ASTHelpers.getSymbol(tree))).addFix(SuggestedFix.replace(state.getEndPosition(ASTHelpers.getReceiver(tree)),state.getEndPosition(tree),"")).build();
}
