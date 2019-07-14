@Override public Description matchCompoundAssignment(CompoundAssignmentTree tree,VisitorState state){
  return matchDivZero(tree,tree.getExpression(),state);
}
