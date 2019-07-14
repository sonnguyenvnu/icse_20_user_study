@Override public Description matchMethodInvocation(MethodInvocationTree methodInvocationTree,VisitorState state){
  if (!matcher.matches(methodInvocationTree,state)) {
    return Description.NO_MATCH;
  }
  List<? extends ExpressionTree> arguments=methodInvocationTree.getArguments();
  ExpressionTree stringLiteralValue=arguments.get(0);
  Fix fix;
  if (arguments.size() == 2) {
    fix=SuggestedFix.swap(arguments.get(0),arguments.get(1));
  }
 else {
    fix=SuggestedFix.delete(state.getPath().getParentPath().getLeaf());
  }
  return describeMatch(stringLiteralValue,fix);
}
