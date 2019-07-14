@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (PERIOD_FROM.matches(tree,state)) {
    ExpressionTree arg0=tree.getArguments().get(0);
    if (DURATION.matches(arg0,state)) {
      return describeMatch(tree);
    }
    if (PERIOD.matches(arg0,state)) {
      return describeMatch(tree,SuggestedFix.replace(tree,state.getSourceForNode(arg0)));
    }
  }
  return Description.NO_MATCH;
}
