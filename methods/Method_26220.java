@Override public Description matchMethodInvocation(MethodInvocationTree methodInvocationTree,VisitorState state){
  if (!MATCHER.matches(methodInvocationTree,state)) {
    return Description.NO_MATCH;
  }
  if (methodInvocationTree.getArguments().size() < 2) {
    return Description.NO_MATCH;
  }
  List<? extends ExpressionTree> args=methodInvocationTree.getArguments();
  int numArgs=args.size();
  for (int i=1; i < numArgs; i++) {
    if (!ASTHelpers.sameVariable(args.get(0),args.get(i))) {
      continue;
    }
    String nullArgSource=state.getSourceForNode(args.get(0));
    if (numArgs == 2 && i == 1) {
      return buildDescription(args.get(1)).setMessage(String.format(MESSAGE,nullArgSource)).addFix(SuggestedFix.replace(args.get(1),String.format("\"%s must not be null\"",nullArgSource))).build();
    }
    return buildDescription(args.get(i)).setMessage(String.format(MESSAGE,nullArgSource)).build();
  }
  return Description.NO_MATCH;
}
