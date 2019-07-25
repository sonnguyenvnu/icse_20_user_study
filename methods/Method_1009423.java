/** 
 * Returns a substring of the given string value from the given begin index to the given end index as a long. If the substring is empty, then -1 will be returned
 * @param value      The string value to return a substring as long for.
 * @param beginIndex The begin index of the substring to be returned as long.
 * @param endIndex   The end index of the substring to be returned as long.
 * @return A substring of the given string value as long or -1 if substring is empty.
 */
private long sublong(String value,int beginIndex,int endIndex){
  String substring=value.substring(beginIndex,endIndex);
  return (substring.length() > 0) ? Long.parseLong(substring) : -1;
}
