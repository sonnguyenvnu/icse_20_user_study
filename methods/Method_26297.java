@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (CALLS_TO_SYSTEM_EXIT_OUTSIDE_MAIN.matches(tree,state)) {
    Optional<? extends Tree> mainMethodInThisClass=ASTHelpers.findEnclosingNode(state.getPath(),ClassTree.class).getMembers().stream().filter(t -> t instanceof MethodTree).filter(t -> MAIN_METHOD.matches((MethodTree)t,state)).findAny();
    return mainMethodInThisClass.isPresent() ? Description.NO_MATCH : describeMatch(tree);
  }
  return Description.NO_MATCH;
}
