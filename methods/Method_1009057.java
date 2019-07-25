/** 
 * Returns the current open element.
 * @return The current open element; or <code>null</code> if none.
 */
public OpenElementEvent current(){
  if (!isEmpty())   return this.openElements[this.size - 1];
 else   return null;
}
