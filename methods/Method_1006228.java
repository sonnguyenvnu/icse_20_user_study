@Override public String format(String fieldText){
  if (fieldText == null) {
    return null;
  }
  return fieldText.toLowerCase(Locale.ROOT);
}
