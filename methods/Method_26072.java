private Description removeMathRoundCall(MethodInvocationTree tree,VisitorState state){
  if (ROUND_CALLS_WITH_INT_ARG.matches(tree,state)) {
    if (ASTHelpers.requiresParentheses(Iterables.getOnlyElement(tree.getArguments()),state)) {
      return describeMatch(tree,SuggestedFix.builder().prefixWith(tree,"(").replace(tree,state.getSourceForNode(tree.getArguments().get(0))).postfixWith(tree,")").build());
    }
    return describeMatch(tree,SuggestedFix.replace(tree,state.getSourceForNode(tree.getArguments().get(0))));
  }
 else   if (ROUND_CALLS_WITH_LONG_ARG.matches(tree,state)) {
    return describeMatch(tree,SuggestedFix.builder().addImport("com.google.common.primitives.Ints").prefixWith(tree,"Ints.saturatedCast(").replace(tree,state.getSourceForNode(tree.getArguments().get(0))).postfixWith(tree,")").build());
  }
  throw new AssertionError("Unknown argument type to round call: " + state.getSourceForNode(tree));
}
