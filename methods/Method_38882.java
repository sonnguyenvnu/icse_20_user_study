/** 
 * Returns <code>true</code> if node has child nodes.
 */
public boolean hasChildNodes(){
  if (childNodes == null) {
    return false;
  }
  return !childNodes.isEmpty();
}
