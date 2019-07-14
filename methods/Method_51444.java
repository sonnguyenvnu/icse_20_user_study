/** 
 * Returns true if the multi value delimiter is present in the string.
 * @param value String
 * @return boolean
 */
private boolean containsDelimiter(String value){
  return value.indexOf(multiValueDelimiter()) >= 0;
}
