private PathElement getDoBranchNodeInLevel(DefaultMutableTreeNode level){
  DataFlowNode inode=currentPath.getLast();
  if (!inode.isType(NodeType.DO_EXPR)) {
    return null;
  }
  int childCount=level.getChildCount();
  DefaultMutableTreeNode child;
  for (int i=0; i < childCount; i++) {
    child=(DefaultMutableTreeNode)level.getChildAt(i);
    PathElement pe=(PathElement)child.getUserObject();
    if (inode.equals(pe.node)) {
      return pe;
    }
  }
  return null;
}
