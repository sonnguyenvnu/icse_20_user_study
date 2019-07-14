@Override public void loadPreferences(Map<String,String> preferences){
  escapeUnicodeCharactersCheckBox.setSelected("true".equals(preferences.get(ESCAPE_UNICODE_CHARACTERS)));
  realignLineNumbersCheckBox.setSelected(!"false".equals(preferences.get(REALIGN_LINE_NUMBERS)));
}
