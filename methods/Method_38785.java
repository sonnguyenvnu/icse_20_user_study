protected String extractValue(String value){
  quoteChar=value.charAt(0);
  if (quoteChar != '"' && quoteChar != '\'') {
    quoteChar=0;
  }
  if (quoteChar != 0) {
    value=value.substring(1,value.length() - 1);
  }
  return value.trim();
}
