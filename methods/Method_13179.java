@Override public boolean arePreferencesValid(){
  return CONTROL_PATTERN.matcher(filtersTextArea.getText()).matches();
}
