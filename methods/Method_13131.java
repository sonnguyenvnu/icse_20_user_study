@SuppressWarnings("unchecked") public void show(Point location,Collection<DelegatingFilterContainer> containers,int locationCount,Consumer<URI> selectedEntryCallback,Runnable closeCallback){
  this.selectedEntryCallback=selectedEntryCallback;
  this.closeCallback=closeCallback;
  SwingUtil.invokeLater(() -> {
    T root=(T)selectLocationTree.getModel().getRoot();
    root.removeAllChildren();
    ArrayList<DelegatingFilterContainer> sortedContainers=new ArrayList<>(containers);
    sortedContainers.sort(DELEGATING_FILTER_CONTAINER_COMPARATOR);
    for (    DelegatingFilterContainer container : sortedContainers) {
      Container.Entry parentEntry=container.getRoot().getParent();
      TreeNodeFactory factory=api.getTreeNodeFactory(parentEntry);
      if (factory != null) {
        T node=factory.make(api,parentEntry);
        if (node != null) {
          root.add(node);
          populate(container.getUris(),node);
        }
      }
    }
    ((DefaultTreeModel)selectLocationTree.getModel()).reload();
    for (int row=0; row < selectLocationTree.getRowCount(); row++) {
      selectLocationTree.expandRow(row);
    }
    T node=root;
    while (true) {
      if (node.getChildCount() == 0) {
        break;
      }
      node=(T)node.getChildAt(0);
    }
    selectLocationTree.setSelectionPath(new TreePath(node.getPath()));
    selectLocationTree.setPreferredSize(null);
    Dimension ps=selectLocationTree.getPreferredSize();
    if (ps.width < 200)     ps.width=200;
    if (ps.height < 50)     ps.height=50;
    selectLocationTree.setPreferredSize(ps);
    selectLocationLabel.setText("" + locationCount + " locations:");
    selectLocationDialog.pack();
    selectLocationDialog.setLocation(location);
    selectLocationDialog.setVisible(true);
    selectLocationTree.requestFocus();
  }
);
}
