protected boolean doMatch(final String thisField,final String thatField){
  return Strings.isNullOrEmpty(thisField) || thisField.equals(thatField);
}
