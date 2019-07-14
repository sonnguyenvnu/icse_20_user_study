@Override public void savePreferences(Map<String,String> preferences){
  preferences.put(ESCAPE_UNICODE_CHARACTERS,Boolean.toString(escapeUnicodeCharactersCheckBox.isSelected()));
  preferences.put(REALIGN_LINE_NUMBERS,Boolean.toString(realignLineNumbersCheckBox.isSelected()));
}
