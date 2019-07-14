@Override public void loadPreferences(Map<String,String> preferences){
  singleInstanceTabsCheckBox.setSelected("true".equals(preferences.get(SINGLE_INSTANCE)));
}
