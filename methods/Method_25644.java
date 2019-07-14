@Override public Description matchBinary(BinaryTree tree,VisitorState state){
  if (!state.getTypes().isSameType(getType(tree),state.getSymtab().stringType)) {
    return NO_MATCH;
  }
  if (tree.getKind() == Kind.PLUS) {
    handleStringifiedTree(tree.getLeftOperand(),ToStringKind.IMPLICIT,state);
    handleStringifiedTree(tree.getRightOperand(),ToStringKind.IMPLICIT,state);
  }
  if (tree.getKind() == Kind.PLUS_ASSIGNMENT) {
    handleStringifiedTree(tree.getRightOperand(),ToStringKind.IMPLICIT,state);
  }
  return NO_MATCH;
}
