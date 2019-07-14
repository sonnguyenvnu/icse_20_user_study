@Override public Description matchMethodInvocation(MethodInvocationTree invocationTree,final VisitorState state){
  if (!STATIC_EQUALS_MATCHER.matches(invocationTree,state) && !INSTANCE_EQUALS_MATCHER.matches(invocationTree,state)) {
    return Description.NO_MATCH;
  }
  Type receiverType;
  Type argumentType;
  if (STATIC_EQUALS_MATCHER.matches(invocationTree,state)) {
    receiverType=ASTHelpers.getType(invocationTree.getArguments().get(0));
    argumentType=ASTHelpers.getType(invocationTree.getArguments().get(1));
  }
 else {
    receiverType=ASTHelpers.getReceiverType(invocationTree);
    argumentType=ASTHelpers.getType(invocationTree.getArguments().get(0));
  }
  TypeCompatibilityReport compatibilityReport=compatibilityOfTypes(receiverType,argumentType,state);
  if (compatibilityReport.compatible()) {
    return Description.NO_MATCH;
  }
  if (ASSERT_FALSE_MATCHER.matches(state.getPath().getParentPath().getLeaf(),state)) {
    return Description.NO_MATCH;
  }
  return buildDescription(invocationTree).setMessage(getMessage(invocationTree,receiverType,argumentType,compatibilityReport.lhs(),compatibilityReport.rhs(),state)).build();
}
