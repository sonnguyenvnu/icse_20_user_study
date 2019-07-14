/** 
 * Does the work of getting the path.
 * @param filename         the filename
 * @param includeSeparator true to include the end separator
 * @return the path
 */
private static String doGetFullPath(String filename,boolean includeSeparator){
  if (filename == null) {
    return null;
  }
  int prefix=getPrefixLength(filename);
  if (prefix < 0) {
    return null;
  }
  if (prefix >= filename.length()) {
    if (includeSeparator) {
      return getPrefix(filename);
    }
 else {
      return filename;
    }
  }
  int index=indexOfLastSeparator(filename);
  if (index < 0) {
    return filename.substring(0,prefix);
  }
  int end=index + (includeSeparator ? 1 : 0);
  if (end == 0) {
    end++;
  }
  return filename.substring(0,end);
}
