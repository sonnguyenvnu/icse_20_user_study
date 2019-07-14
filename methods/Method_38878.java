/** 
 * Returns <code>true</code> if node has attributes.
 */
public boolean hasAttributes(){
  if (attributes == null) {
    return false;
  }
  return !attributes.isEmpty();
}
