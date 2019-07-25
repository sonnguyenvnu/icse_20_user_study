@Override public String format(String fieldText){
  if (fieldText == null) {
    return null;
  }
  return fieldText.toUpperCase(Locale.ROOT);
}
