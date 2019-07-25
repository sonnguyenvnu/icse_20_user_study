@Override public String format(String fieldText){
  return ((fieldText == null) || fieldText.isEmpty()) ? defValue : fieldText;
}
