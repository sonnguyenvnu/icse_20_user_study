protected void onTypeSelected(){
  TreeNode selectedTreeNode=(TreeNode)openTypeHierarchyTree.getLastSelectedPathComponent();
  if (selectedTreeNode != null) {
    TreePath path=new TreePath(selectedTreeNode.getPath());
    Rectangle bounds=openTypeHierarchyTree.getPathBounds(path);
    Point listLocation=openTypeHierarchyTree.getLocationOnScreen();
    Point leftBottom=new Point(listLocation.x + bounds.x,listLocation.y + bounds.y + bounds.height);
    selectedTypeCallback.accept(leftBottom,selectedTreeNode.entries,selectedTreeNode.typeName);
  }
}
