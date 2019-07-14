private static String getArgument(VisitorState state,ExpressionTree tree){
  return state.getSourceForNode(getOnlyElement(((MethodInvocationTree)tree).getArguments()));
}
