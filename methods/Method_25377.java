public static Matcher<MethodInvocationTree> receiverOfInvocation(final Matcher<ExpressionTree> expressionTreeMatcher){
  return (methodInvocationTree,state) -> {
    ExpressionTree receiver=ASTHelpers.getReceiver(methodInvocationTree);
    return receiver != null && expressionTreeMatcher.matches(receiver,state);
  }
;
}
