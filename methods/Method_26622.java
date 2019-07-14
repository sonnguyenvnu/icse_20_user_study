@Override public Choice<Unifier> visitAssignment(AssignmentTree assign,Unifier unifier){
  return getVariable().unify(assign.getVariable(),unifier).thenChoose(unifications(getExpression(),assign.getExpression()));
}
