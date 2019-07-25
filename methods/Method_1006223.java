@Override public String format(String fieldText){
  if ((fieldText == null) || (regex == null)) {
    return fieldText;
  }
  return fieldText.replaceAll(regex,replaceWith);
}
