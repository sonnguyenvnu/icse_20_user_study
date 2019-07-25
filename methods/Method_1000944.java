/** 
 * Alternative method that we need to avoid bumping into NPE issues with auto-unboxing.
 * @return This node (to allow chaining)
 */
public ObjectNode put(String fieldName,Boolean v){
  return _put(fieldName,(v == null) ? nullNode() : booleanNode(v.booleanValue()));
}
