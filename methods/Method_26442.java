@Override public Description matchMethodInvocation(MethodInvocationTree methodInvocationTree,VisitorState state){
  if (methodInvocationTree.getArguments().isEmpty()) {
    return Description.NO_MATCH;
  }
  if (!TRUTH_SUBJECT_CALL.matches(methodInvocationTree,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree rec=ASTHelpers.getReceiver(methodInvocationTree);
  if (rec == null) {
    return Description.NO_MATCH;
  }
  if (!ASSERT_THAT.matches(rec,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree expr=getOnlyElement(((MethodInvocationTree)rec).getArguments());
  if (expr == null) {
    return Description.NO_MATCH;
  }
  if (ASTHelpers.constValue(expr) == null) {
    return Description.NO_MATCH;
  }
  ExpressionTree expectation=getOnlyElement(methodInvocationTree.getArguments());
  if (ASTHelpers.constValue(expectation) != null) {
    return Description.NO_MATCH;
  }
  SuggestedFix fix=SuggestedFix.swap(expr,expectation);
  return describeMatch(methodInvocationTree,fix);
}
