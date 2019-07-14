@Override public Choice<State<JCEnhancedForLoop>> visitEnhancedForLoop(final EnhancedForLoopTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getExpression(),s),s -> unifyStatement(node.getStatement(),s),(expr,stmt) -> maker().ForeachLoop((JCVariableDecl)node.getVariable(),expr,stmt));
}
