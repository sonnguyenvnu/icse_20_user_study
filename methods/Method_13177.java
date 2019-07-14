@Override public void loadPreferences(Map<String,String> preferences){
  boolean enabled=!"false".equals(preferences.get(ACTIVATED));
  enableCheckBox.setSelected(enabled);
  filtersTextArea.setEnabled(enabled);
  resetButton.setEnabled(enabled);
  String filters=preferences.get(FILTERS);
  filtersTextArea.setText((filters == null) || filters.isEmpty() ? DEFAULT_FILTERS_VALUE : filters);
}
