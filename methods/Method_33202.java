@Override public int getTreeItemLevel(TreeItem<?> node){
  final TreeItem<?> root=getRoot();
  if (node == null) {
    return -1;
  }
  if (node == root) {
    return 0;
  }
  int level=0;
  TreeItem<?> parent=node.getParent();
  while (parent != null) {
    level++;
    if (parent == root) {
      break;
    }
    if (parent.getValue() != null && parent.getValue() instanceof RecursiveTreeObject && ((RecursiveTreeObject<?>)parent.getValue()).getGroupedColumn() != null) {
      level--;
    }
    parent=parent.getParent();
  }
  return level;
}
