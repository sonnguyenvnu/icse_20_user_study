@SuppressWarnings("deprecation") public String compact(String message){
  if (fExpected == null || fActual == null || areStringsEqual()) {
    return Assert.format(message,fExpected,fActual);
  }
  findCommonPrefix();
  findCommonSuffix();
  String expected=compactString(fExpected);
  String actual=compactString(fActual);
  return Assert.format(message,expected,actual);
}
