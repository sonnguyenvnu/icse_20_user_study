/** 
 * Returns <code>true</code> if descriptor content matches required declared flag.
 */
public boolean matchDeclared(final boolean declared){
  if (!declared) {
    return isPublic;
  }
  return true;
}
