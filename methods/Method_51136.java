private void removeFromTree(){
  DefaultMutableTreeNode last=stack.getLastLeaf();
  if (last == null) {
    System.out.println("removeFromTree - last == null");
    return;
  }
  DefaultMutableTreeNode parent=(DefaultMutableTreeNode)last.getParent();
  if (parent != null) {
    parent.remove(last);
  }
  last=stack.getLastLeaf();
  if (last == null || last.getUserObject() == null) {
    return;
  }
  PathElement e=(PathElement)last.getUserObject();
  if (e != null && e.isPseudoPathElement()) {
    this.removeFromTree();
  }
}
