protected void updateTree(Container.Entry entry,String typeName){
  SwingUtil.invokeLater(() -> {
    DefaultTreeModel model=(DefaultTreeModel)openTypeHierarchyTree.getModel();
    DefaultMutableTreeNode root=(DefaultMutableTreeNode)model.getRoot();
    root.removeAllChildren();
    TreeNode selectedTreeNode=createTreeNode(entry,typeName);
    TreeNode parentTreeNode=createParentTreeNode(selectedTreeNode);
    root.add(parentTreeNode);
    model.reload();
    if (selectedTreeNode != null) {
      TreePath path=new TreePath(selectedTreeNode.getPath());
      openTypeHierarchyTree.expandPath(path);
      openTypeHierarchyTree.makeVisible(path);
      Rectangle bounds=openTypeHierarchyTree.getPathBounds(path);
      if (bounds != null) {
        bounds.x=0;
        Rectangle lastRowBounds=openTypeHierarchyTree.getRowBounds(openTypeHierarchyTree.getRowCount() - 1);
        if (lastRowBounds != null) {
          bounds.y=Math.max(bounds.y - 30,0);
          bounds.height=Math.min(bounds.height + bounds.y + 60,lastRowBounds.height + lastRowBounds.y) - bounds.y;
        }
        openTypeHierarchyTree.scrollRectToVisible(bounds);
        openTypeHierarchyTree.scrollPathToVisible(path);
        openTypeHierarchyTree.fireVisibleDataPropertyChange();
      }
      openTypeHierarchyTree.setSelectionPath(path);
    }
  }
);
}
