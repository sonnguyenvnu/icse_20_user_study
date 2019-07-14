/** 
 * Extract the first name of this reference.
 */
@Override public String extractThisReference(final String propertyName){
  int ndx=StringUtil.indexOfChars(propertyName,INDEX_CHARS);
  if (ndx == -1) {
    return propertyName;
  }
  return propertyName.substring(0,ndx);
}
