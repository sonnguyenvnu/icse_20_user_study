private void updateDisclosureNode(){
  Node disclosureNode=((JFXTreeTableCell<S,T>)getSkinnable()).getDisclosureNode();
  if (disclosureNode != null) {
    TreeItem<S> item=getSkinnable().getTreeTableRow().getTreeItem();
    final S value=item == null ? null : item.getValue();
    boolean disclosureVisible=value != null && !item.isLeaf() && value instanceof RecursiveTreeObject && ((RecursiveTreeObject)value).getGroupedColumn() == getSkinnable().getTableColumn();
    disclosureNode.setVisible(disclosureVisible);
    if (!disclosureVisible) {
      getChildren().remove(disclosureNode);
    }
 else     if (disclosureNode.getParent() == null) {
      getChildren().add(disclosureNode);
      disclosureNode.toFront();
    }
 else {
      disclosureNode.toBack();
    }
    if (disclosureNode.getScene() != null) {
      disclosureNode.applyCss();
    }
  }
}
