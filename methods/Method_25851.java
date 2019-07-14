@Override public Description matchMethodInvocation(MethodInvocationTree build,VisitorState state){
  if (!BUILD.matches(build,state)) {
    return NO_MATCH;
  }
  ExpressionTree addFix=ASTHelpers.getReceiver(build);
  if (!ADD_FIX.matches(addFix,state)) {
    return NO_MATCH;
  }
  ExpressionTree buildDescription=ASTHelpers.getReceiver(addFix);
  if (!BUILD_DESCRIPTION.matches(buildDescription,state)) {
    return NO_MATCH;
  }
  return describeMatch(build,SuggestedFix.replace(build,String.format("describeMatch(%s, %s)",getArgument(state,buildDescription),getArgument(state,addFix))));
}
