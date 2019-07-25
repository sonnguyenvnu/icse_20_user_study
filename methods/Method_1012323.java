@Override public void persist(){
  if (variantsList.size() > 0) {
    final StringBuilder stringBuilder=new StringBuilder(variantsList.size() * EMOJI_GUESS_SIZE);
    for (int i=0; i < variantsList.size(); i++) {
      stringBuilder.append(variantsList.get(i).getUnicode()).append(EMOJI_DELIMITER);
    }
    stringBuilder.setLength(stringBuilder.length() - EMOJI_DELIMITER.length());
    getPreferences().edit().putString(VARIANT_EMOJIS,stringBuilder.toString()).apply();
  }
 else {
    getPreferences().edit().remove(VARIANT_EMOJIS).apply();
  }
}
