@Override ExpressionTree extractSourceTree(MethodInvocationTree tree,VisitorState state){
  return Iterables.get(tree.getArguments(),methodArgIndex);
}
