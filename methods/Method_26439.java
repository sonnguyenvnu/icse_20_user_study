@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCH.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  if (expectedValue(tree,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree assertion=findReceiverMatching(tree,state,ASSERT_ON_EXPECTED);
  if (!(assertion instanceof MethodInvocationTree)) {
    return Description.NO_MATCH;
  }
  ExpressionTree assertedArgument=getOnlyElement(((MethodInvocationTree)assertion).getArguments());
  ExpressionTree terminatingArgument=getOnlyElement(tree.getArguments());
  if (ASTHelpers.constValue(terminatingArgument) != null || Matchers.staticFieldAccess().matches(terminatingArgument,state) || isConstantCreator(terminatingArgument,state)) {
    return Description.NO_MATCH;
  }
  SuggestedFix fix=SuggestedFix.swap(assertedArgument,terminatingArgument);
  if (SuggestedFixes.compilesWithFix(fix,state)) {
    return describeMatch(tree,fix);
  }
  return describeMatch(tree);
}
