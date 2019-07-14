@Override public Choice<State<JCAssign>> visitAssignment(final AssignmentTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getVariable(),s),s -> unifyExpression(node.getExpression(),s),maker()::Assign).condition(s -> !(s.result().getVariable() instanceof PlaceholderParamIdent));
}
