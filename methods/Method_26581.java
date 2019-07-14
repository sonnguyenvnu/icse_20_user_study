@Override public Choice<State<JCWhileLoop>> visitWhileLoop(final WhileLoopTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getCondition(),s),s -> unifyStatement(node.getStatement(),s),maker()::WhileLoop);
}
