@Override public <T extends JComponent & UriGettable>void pageChanged(T page){
  Component subPage=tabbedPane.getSelectedComponent();
  if (subPage != null) {
    ((JComponent)subPage).putClientProperty("currentPage",page);
  }
  if (page == null) {
    page=(T)subPage;
  }
  for (  PageChangeListener listener : pageChangedListeners) {
    listener.pageChanged(page);
  }
}
