/** 
 * Returns the HTML filename (including path prefix if necessary) for this keyword, or null if it doesn't exist.
 */
public String lookupReference(String keyword){
  return keywordToReference.get(keyword);
}
