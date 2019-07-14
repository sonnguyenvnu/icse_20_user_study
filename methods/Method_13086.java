@SuppressWarnings("unchecked") protected <P extends JComponent & UriGettable>void pageChanged(){
  try {
    openUriEnabled=false;
    P page=(P)tabbedPanel.tabbedPane.getSelectedComponent();
    if (updateTreeMenuEnabled) {
      if (page != null) {
        T node=(T)page.getClientProperty("node");
        TreePath treePath=new TreePath(node.getPath());
        tree.setSelectionPath(treePath);
        tree.scrollPathToVisible(treePath);
      }
 else {
        tree.clearSelection();
      }
    }
    for (    PageChangeListener listener : pageChangedListeners) {
      listener.pageChanged(page);
    }
  }
  finally {
    openUriEnabled=true;
  }
}
