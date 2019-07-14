/** 
 * Returns whether an attribute exists.
 * @webref xml:method
 * @brief Checks whether or not an element has the specified attribute
 */
public boolean hasAttribute(String name){
  return (node.getAttributes().getNamedItem(name) != null);
}
