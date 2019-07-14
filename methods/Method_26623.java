@Override public Choice<Unifier> visitCompoundAssignment(CompoundAssignmentTree assignOp,Unifier unifier){
  return Choice.condition(getKind() == assignOp.getKind(),unifier).thenChoose(unifications(getVariable(),assignOp.getVariable())).thenChoose(unifications(getExpression(),assignOp.getExpression()));
}
