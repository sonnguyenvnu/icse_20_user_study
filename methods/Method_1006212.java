@Override public String format(String fieldText){
  if ((fieldText == null) || fieldText.isEmpty() || (pluralText == null)) {
    return "";
  }
  if (fieldText.matches(".*\\sand\\s.*")) {
    return pluralText;
  }
 else {
    return singularText;
  }
}
