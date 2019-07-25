/** 
 * Method for setting value of a field to specified String value.
 * @return This node (to allow chaining)
 */
public ObjectNode put(String fieldName,boolean v){
  return _put(fieldName,booleanNode(v));
}
