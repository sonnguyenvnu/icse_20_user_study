@Override public void savePreferences(Map<String,String> preferences){
  preferences.put(ACTIVATED,Boolean.toString(enableCheckBox.isSelected()));
  preferences.put(FILTERS,filtersTextArea.getText().trim());
}
