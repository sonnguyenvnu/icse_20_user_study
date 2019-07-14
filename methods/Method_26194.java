@Override public Description matchBinary(BinaryTree tree,VisitorState state){
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (!(parent instanceof BinaryTree)) {
    return NO_MATCH;
  }
  if (TreeInfo.opPrec(((JCBinary)tree).getTag()) == TreeInfo.opPrec(((JCBinary)parent).getTag())) {
    return NO_MATCH;
  }
  if (!isConfusing(tree.getKind(),parent.getKind())) {
    return NO_MATCH;
  }
  return createAppropriateFix(tree,state);
}
