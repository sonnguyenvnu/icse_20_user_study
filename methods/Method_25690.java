@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (!Matchers.AUTOVALUE_CONSTRUCTOR.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  InvocationInfo invocationInfo=InvocationInfo.createFromNewClass(tree,ASTHelpers.getSymbol(tree),state);
  Changes changes=argumentChangeFinder.findChanges(invocationInfo);
  if (changes.isEmpty()) {
    return Description.NO_MATCH;
  }
  return describeMatch(invocationInfo.tree(),changes.buildPermuteArgumentsFix(invocationInfo));
}
