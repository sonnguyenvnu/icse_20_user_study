private int countLoops(){
  DefaultMutableTreeNode treeNode=stack.getLastLeaf();
  int counter=0;
  if (treeNode.getParent() != null) {
    int childCount=treeNode.getParent().getChildCount();
    for (int i=0; i < childCount; i++) {
      DefaultMutableTreeNode tNode=(DefaultMutableTreeNode)treeNode.getParent().getChildAt(i);
      PathElement e=(PathElement)tNode.getUserObject();
      if (e != null && !e.isPseudoPathElement()) {
        counter++;
      }
    }
  }
  return counter;
}
