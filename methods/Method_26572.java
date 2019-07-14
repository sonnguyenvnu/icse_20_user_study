@Override public Choice<State<JCInstanceOf>> visitInstanceOf(final InstanceOfTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getExpression(),s),expr -> maker().TypeTest(expr,(JCTree)node.getType()));
}
