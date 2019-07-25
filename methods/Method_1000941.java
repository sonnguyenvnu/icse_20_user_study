/** 
 * Method for removing field entry from this ObjectNode, and returning instance after removal.
 * @return This node after removing entry (if any)
 */
public ObjectNode without(String fieldName){
  _children.remove(fieldName);
  return this;
}
