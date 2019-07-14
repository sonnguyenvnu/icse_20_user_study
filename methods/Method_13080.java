@SuppressWarnings("unchecked") protected T showPage(URI uri){
  String u1=uri.getPath();
  int i=tabbedPane.getTabCount();
  while (i-- > 0) {
    T page=(T)tabbedPane.getComponentAt(i);
    String u2=page.getUri().getPath();
    if (u1.startsWith(u2)) {
      tabbedPane.setSelectedIndex(i);
      return page;
    }
  }
  return null;
}
