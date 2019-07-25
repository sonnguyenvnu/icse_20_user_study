/** 
 * Returns whether specified document is in this pane or not.
 * @param document document to look for
 * @return true if specified document is in this pane, false otherwise
 */
public boolean contains(final T document){
  return contains(document.getId());
}
