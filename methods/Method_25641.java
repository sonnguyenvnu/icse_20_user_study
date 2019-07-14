/** 
 * Don't match the method that is invoked through  {@code Mockito.verify(t)} or {@code doReturn(val).when(t)}.
 */
private static boolean mockitoInvocation(Tree tree,VisitorState state){
  if (!(tree instanceof JCMethodInvocation)) {
    return false;
  }
  JCMethodInvocation invocation=(JCMethodInvocation)tree;
  if (!(invocation.getMethodSelect() instanceof JCFieldAccess)) {
    return false;
  }
  ExpressionTree receiver=ASTHelpers.getReceiver(invocation);
  return MOCKITO_MATCHER.matches(receiver,state);
}
