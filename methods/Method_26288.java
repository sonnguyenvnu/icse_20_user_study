@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  Optional<Fix> fix=buildFix(tree,state);
  if (!fix.isPresent()) {
    return NO_MATCH;
  }
  return describeMatch(tree,fix.get());
}
