protected void setSelectedIndex(int index){
  if (index != -1) {
    if (tabbedPane.getTabLayoutPolicy() == JTabbedPane.SCROLL_TAB_LAYOUT) {
      ChangeEvent event=new ChangeEvent(tabbedPane);
      for (      ChangeListener listener : tabbedPane.getChangeListeners()) {
        if (listener.getClass().getPackage().getName().startsWith("javax.")) {
          listener.stateChanged(event);
        }
      }
    }
    tabbedPane.setSelectedIndex(index);
  }
}
