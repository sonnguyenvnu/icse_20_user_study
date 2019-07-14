private static boolean isInVarargsPosition(ExpressionTree argTree,MethodInvocationTree methodInvocationTree,VisitorState state){
  int parameterCount=getSymbol(methodInvocationTree).getParameters().size();
  List<? extends ExpressionTree> arguments=methodInvocationTree.getArguments();
  return (arguments.size() > parameterCount || !state.getTypes().isArray(getType(argTree))) && arguments.indexOf(argTree) >= parameterCount - 1;
}
