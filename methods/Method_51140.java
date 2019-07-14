private boolean equalsPseudoPathElementWithDoBranchNodeInLevel(DefaultMutableTreeNode level){
  DataFlowNode inode=currentPath.getLast();
  if (!inode.isType(NodeType.DO_EXPR)) {
    return false;
  }
  int childCount=level.getChildCount();
  DefaultMutableTreeNode child;
  for (int i=0; i < childCount; i++) {
    child=(DefaultMutableTreeNode)level.getChildAt(i);
    PathElement pe=(PathElement)child.getUserObject();
    if (pe != null && pe.isPseudoPathElement() && pe.pseudoRef.equals(inode)) {
      return true;
    }
  }
  return false;
}
