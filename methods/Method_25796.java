@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!NEW_INSTANCE.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  SuggestedFix.Builder fix=SuggestedFix.builder();
  fix.replace(state.getEndPosition(ASTHelpers.getReceiver(tree)),state.getEndPosition(tree),".getDeclaredConstructor().newInstance()");
  boolean fixedExceptions=fixExceptions(state,fix);
  if (!fixedExceptions) {
    fixThrows(state,fix);
  }
  return describeMatch(tree,fix.build());
}
