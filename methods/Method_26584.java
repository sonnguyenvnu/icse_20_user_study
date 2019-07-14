@Override public Choice<State<JCCatch>> visitCatch(final CatchTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyStatement(node.getBlock(),s),block -> maker().Catch((JCVariableDecl)node.getParameter(),(JCBlock)block));
}
