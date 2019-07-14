private Description provideReplacementForStaticMethodInvocation(BinaryTree tree,MethodInvocationTree callToSize,final VisitorState state,ExpressionType expressionType){
  ExpressionTree classToken=getReceiver(callToSize);
  if (HAS_EMPTY_METHOD.matches(classToken,state)) {
    String argumentString=callToSize.getArguments().stream().map(state::getSourceForNode).collect(joining(","));
    return describeMatch(tree,SuggestedFix.replace(tree,"!" + state.getSourceForNode(classToken) + ".isEmpty(" + argumentString + ")"));
  }
 else {
    return removeEqualsFromComparison(tree,state,expressionType);
  }
}
