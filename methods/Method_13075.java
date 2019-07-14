@Override public void preferencesChanged(Map<String,String> preferences){
  super.preferencesChanged(preferences);
  Component subPage=tabbedPane.getSelectedComponent();
  if (subPage instanceof PreferencesChangeListener) {
    ((PreferencesChangeListener)subPage).preferencesChanged(preferences);
  }
}
