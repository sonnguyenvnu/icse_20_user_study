/** 
 * Method for removing specified field properties out of this ObjectNode.
 * @param fieldNames Names of fields to remove
 * @return This node after removing entries
 */
public ObjectNode without(Collection<String> fieldNames){
  _children.keySet().removeAll(fieldNames);
  return this;
}
