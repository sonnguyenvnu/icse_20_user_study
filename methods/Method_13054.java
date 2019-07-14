@Override public void savePreferences(Map<String,String> preferences){
  preferences.put(TAB_LAYOUT,Boolean.toString(singleLineTabsCheckBox.isSelected()));
}
