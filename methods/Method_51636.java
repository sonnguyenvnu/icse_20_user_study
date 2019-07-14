private void loadSymbolTableTreeData(TreeNode rootNode){
  if (rootNode != null) {
    symbolTableTreeWidget.setModel(new DefaultTreeModel(rootNode));
    symbolTableTreeWidget.expandAll(true);
  }
 else {
    symbolTableTreeWidget.setModel(null);
  }
}
