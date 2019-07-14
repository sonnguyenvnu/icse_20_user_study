@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  MethodSymbol symbol=ASTHelpers.getSymbol(tree);
  if (symbol == null) {
    return Description.NO_MATCH;
  }
  if (Matchers.ASSERT_METHOD.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  return visitNewClassOrMethodInvocation(InvocationInfo.createFromMethodInvocation(tree,symbol,state));
}
