@Override public Description matchCompoundAssignment(CompoundAssignmentTree tree,VisitorState state){
  if (state.getTypes().isSameType(getType(tree.getVariable()),state.getSymtab().stringType) && tree.getKind() == Kind.PLUS_ASSIGNMENT) {
    handleStringifiedTree(tree.getExpression(),ToStringKind.IMPLICIT,state);
  }
  return NO_MATCH;
}
