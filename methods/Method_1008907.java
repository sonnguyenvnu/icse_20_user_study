/** 
 * Convert radix point to ".", and remove thousands separator, in preparation for our regex.
 */
private String prepare(String string){
  if (".".equals(groupingSeparator)) {
    string=string.replaceAll("\\" + groupingSeparator,"");
  }
 else {
    string=string.replaceAll(groupingSeparator,"");
  }
  if (decimalSymbol.equals(".")) {
    return string;
  }
 else {
    return string.replace(decimalSymbol,".");
  }
}
