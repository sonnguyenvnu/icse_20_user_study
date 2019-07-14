public void updateTree(Collection<Future<Indexes>> collectionOfFutureIndexes){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  TreeNode selectedTreeNode=(TreeNode)openTypeHierarchyTree.getLastSelectedPathComponent();
  if (selectedTreeNode != null) {
    updateTree(selectedTreeNode.entry,selectedTreeNode.typeName);
  }
}
