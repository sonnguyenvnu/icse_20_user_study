@Override protected boolean handleDisclosureNode(double x,double y){
  final TreeItem<S> treeItem=getControl().getTreeTableRow().getTreeItem();
  if (!treeItem.isLeaf()) {
    final Node disclosureNode=getControl().getTreeTableRow().getDisclosureNode();
    if (disclosureNode != null) {
      if (disclosureNode.getBoundsInParent().contains(x + disclosureNode.getTranslateX(),y)) {
        if (treeItem != null) {
          treeItem.setExpanded(!treeItem.isExpanded());
        }
        return true;
      }
    }
  }
  return false;
}
