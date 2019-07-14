@Override public void savePreferences(Map<String,String> preferences){
  preferences.put(SINGLE_INSTANCE,Boolean.toString(singleInstanceTabsCheckBox.isSelected()));
}
