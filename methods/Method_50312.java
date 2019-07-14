/** 
 * Does the work of getting the path.
 * @param filename     the filename
 * @param separatorAdd 0 to omit the end separator, 1 to return it
 * @return the path
 */
private static String doGetPath(String filename,int separatorAdd){
  if (filename == null) {
    return null;
  }
  int prefix=getPrefixLength(filename);
  if (prefix < 0) {
    return null;
  }
  int index=indexOfLastSeparator(filename);
  int endIndex=index + separatorAdd;
  if (prefix >= filename.length() || index < 0 || prefix >= endIndex) {
    return "";
  }
  return filename.substring(prefix,endIndex);
}
