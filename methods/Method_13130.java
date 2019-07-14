@SuppressWarnings("unchecked") public void updateTree(Collection<DelegatingFilterContainer> containers,int matchingTypeCount){
  SwingUtil.invokeLater(() -> {
    DefaultTreeModel model=(DefaultTreeModel)searchInConstantPoolsTree.getModel();
    T root=(T)model.getRoot();
    root.removeAllChildren();
    accepted.clear();
    expanded.clear();
    if (containers != null) {
      ArrayList<DelegatingFilterContainer> list=new ArrayList<>(containers);
      list.sort(CONTAINER_COMPARATOR);
      for (      DelegatingFilterContainer container : list) {
        accepted.addAll(container.getUris());
        Container.Entry parentEntry=container.getRoot().getParent();
        TreeNodeFactory treeNodeFactory=api.getTreeNodeFactory(parentEntry);
        if (treeNodeFactory != null) {
          root.add(treeNodeFactory.make(api,parentEntry));
        }
      }
      T node=root;
      while (true) {
        populate(model,node);
        if (node.getChildCount() == 0) {
          break;
        }
        node=(T)node.getChildAt(0);
      }
      searchInConstantPoolsTree.setSelectionPath(new TreePath(node.getPath()));
    }
 else {
      model.reload();
    }
switch (matchingTypeCount) {
case 0:
      searchInConstantPoolsLabel.setText("Matching entries:");
    break;
case 1:
  searchInConstantPoolsLabel.setText("1 matching entry:");
break;
default :
searchInConstantPoolsLabel.setText(matchingTypeCount + " matching entries:");
}
}
);
}
