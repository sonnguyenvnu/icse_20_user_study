@Override public void loadPreferences(Map<String,String> preferences){
  singleLineTabsCheckBox.setSelected("true".equals(preferences.get(TAB_LAYOUT)));
}
