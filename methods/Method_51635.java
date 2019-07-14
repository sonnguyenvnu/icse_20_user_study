private void loadASTTreeData(TreeNode rootNode){
  astTreeWidget.setModel(new DefaultTreeModel(rootNode));
  astTreeWidget.setRootVisible(true);
  astTreeWidget.expandAll(true);
}
