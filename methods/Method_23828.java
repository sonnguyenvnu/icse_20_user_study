/** 
 * Returns a boolean of whether or not there are children.
 * @webref xml:method
 * @brief Checks whether or not an element has any children
 */
public boolean hasChildren(){
  checkChildren();
  return children.length > 0;
}
