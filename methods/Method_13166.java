@Override public void loadPreferences(Map<String,String> preferences){
  writeLineNumbersCheckBox.setSelected(!"false".equals(preferences.get(WRITE_LINE_NUMBERS)));
  writeMetadataCheckBox.setSelected(!"false".equals(preferences.get(WRITE_METADATA)));
}
