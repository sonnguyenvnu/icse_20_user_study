private static SuggestedFix getFix(InstanceOfTree tree,VisitorState state){
  Tree parent=state.getPath().getParentPath().getLeaf();
  Tree grandParent=state.getPath().getParentPath().getParentPath().getLeaf();
  if (parent.getKind() == Kind.PARENTHESIZED && grandParent.getKind() == Kind.LOGICAL_COMPLEMENT) {
    return SuggestedFix.replace(grandParent,state.getSourceForNode(tree.getExpression()) + " == null");
  }
  return SuggestedFix.replace(tree,state.getSourceForNode(tree.getExpression()) + " != null");
}
