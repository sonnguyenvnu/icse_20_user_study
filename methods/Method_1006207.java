@Override public String format(String fieldText){
  if (fieldText == null) {
    return null;
  }
  return DOI.parse(fieldText).map(DOI::getDOI).orElse(fieldText);
}
