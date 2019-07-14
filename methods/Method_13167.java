@Override public void savePreferences(Map<String,String> preferences){
  preferences.put(WRITE_LINE_NUMBERS,Boolean.toString(writeLineNumbersCheckBox.isSelected()));
  preferences.put(WRITE_METADATA,Boolean.toString(writeMetadataCheckBox.isSelected()));
}
