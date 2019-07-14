/** 
 * Create fixes for invalid assertions. <ul> <li>Rewrite `verify(mock.bar())` to `verify(mock).bar()` <li>Rewrite `verify(mock.bar(), times(N))` to `verify(mock, times(N)).bar()` <li>Rewrite `verify(mock, never())` to `verifyZeroInteractions(mock)` <li>Finally, offer to delete the mock statement. </ul>
 */
private void buildFix(Description.Builder builder,MethodInvocationTree tree,VisitorState state){
  MethodInvocationTree mockitoCall=tree;
  List<? extends ExpressionTree> args=mockitoCall.getArguments();
  Tree mock=mockitoCall.getArguments().get(0);
  boolean isVerify=ASTHelpers.getSymbol(tree).getSimpleName().contentEquals("verify");
  if (isVerify && mock.getKind() == Kind.METHOD_INVOCATION) {
    MethodInvocationTree invocation=(MethodInvocationTree)mock;
    String verify=state.getSourceForNode(mockitoCall.getMethodSelect());
    String receiver=state.getSourceForNode(ASTHelpers.getReceiver(invocation));
    String mode=args.size() > 1 ? ", " + state.getSourceForNode(args.get(1)) : "";
    String call=state.getSourceForNode(invocation).substring(receiver.length());
    builder.addFix(SuggestedFix.replace(tree,String.format("%s(%s%s)%s",verify,receiver,mode,call)));
  }
  if (isVerify && args.size() > 1 && NEVER_METHOD.matches(args.get(1),state)) {
    builder.addFix(SuggestedFix.builder().addStaticImport("org.mockito.Mockito.verifyZeroInteractions").replace(tree,String.format("verifyZeroInteractions(%s)",mock)).build());
  }
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (parent.getKind() == Kind.EXPRESSION_STATEMENT) {
    builder.addFix(SuggestedFix.delete(parent));
  }
 else {
    builder.addFix(SuggestedFix.delete(tree));
  }
}
