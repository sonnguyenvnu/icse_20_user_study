private static ImmutableList<ExpressionTree> findActualAndExpectedForPossibleEqualsCall(ExpressionTree possiblyEqualsCall,VisitorState state){
  if (!EQUALS_LIKE_METHOD.matches(possiblyEqualsCall,state)) {
    return null;
  }
  MethodInvocationTree equalsCheck=(MethodInvocationTree)possiblyEqualsCall;
  List<? extends ExpressionTree> args=equalsCheck.getArguments();
  return (args.size() == 2) ? ImmutableList.copyOf(args) : ImmutableList.of(((MemberSelectTree)equalsCheck.getMethodSelect()).getExpression(),getOnlyElement(args));
}
