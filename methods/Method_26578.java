@Override public Choice<State<JCForLoop>> visitForLoop(final ForLoopTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyStatements(node.getInitializer(),s),s -> unifyExpression(node.getCondition(),s),s -> unifyStatements(node.getUpdate(),s),s -> unifyStatement(node.getStatement(),s),(inits,cond,update,stmt) -> maker().ForLoop(inits,cond,List.convert(JCExpressionStatement.class,update),stmt));
}
