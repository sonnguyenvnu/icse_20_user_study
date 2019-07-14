@Override public void preferencesChanged(Map<String,String> preferences){
  this.preferences=preferences;
  if ("true".equals(preferences.get(TAB_LAYOUT))) {
    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
  }
 else {
    tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
  }
  setSelectedIndex(tabbedPane.getSelectedIndex());
}
