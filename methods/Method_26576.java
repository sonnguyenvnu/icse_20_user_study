@Override public Choice<State<JCAssignOp>> visitCompoundAssignment(final CompoundAssignmentTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getVariable(),s),s -> unifyExpression(node.getExpression(),s),(var,expr) -> maker().Assignop(((JCAssignOp)node).getTag(),var,expr)).condition(assignOp -> !(assignOp.result().getVariable() instanceof PlaceholderParamIdent));
}
