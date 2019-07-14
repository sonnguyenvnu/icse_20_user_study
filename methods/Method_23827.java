/** 
 * Returns the number of children.
 * @webref xml:method
 * @brief Returns the element's number of children
 * @return the count.
 */
public int getChildCount(){
  checkChildren();
  return children.length;
}
