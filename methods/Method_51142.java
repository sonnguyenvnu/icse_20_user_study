private PathElement isNodeInLevel(DefaultMutableTreeNode level){
  DataFlowNode inode=currentPath.getLast();
  DefaultMutableTreeNode child=(DefaultMutableTreeNode)level.getFirstChild();
  if (child != null) {
    PathElement levelElement=(PathElement)child.getUserObject();
    if (inode.equals(levelElement.node)) {
      return levelElement;
    }
  }
  return null;
}
