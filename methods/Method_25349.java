@Override public boolean matches(CompoundAssignmentTree compoundAssignmentTree,VisitorState state){
  if (!operators.contains(compoundAssignmentTree.getKind())) {
    return false;
  }
  return receiverMatcher.matches(compoundAssignmentTree.getVariable(),state) && expressionMatcher.matches(compoundAssignmentTree.getExpression(),state);
}
