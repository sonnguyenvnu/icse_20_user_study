@Override public Choice<State<JCSynchronized>> visitSynchronized(final SynchronizedTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getExpression(),s),s -> unifyStatement(node.getBlock(),s),(expr,block) -> maker().Synchronized(expr,(JCBlock)block));
}
