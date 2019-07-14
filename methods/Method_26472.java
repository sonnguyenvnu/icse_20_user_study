private static void fixMethodInvocations(List<MethodInvocationTree> simpleMethodInvocations,SuggestedFix.Builder fixBuilder,VisitorState state){
  for (  MethodInvocationTree methodInvocation : simpleMethodInvocations) {
    ExpressionTree receiver=ASTHelpers.getReceiver(methodInvocation);
    Type receiverType=ASTHelpers.getType(receiver);
    MemberSelectTree methodSelect=(MemberSelectTree)methodInvocation.getMethodSelect();
    fixBuilder.replace(methodInvocation,String.format("%s.%s(%s)",receiverType.tsym.getSimpleName(),methodSelect.getIdentifier(),state.getSourceForNode(receiver)));
  }
}
