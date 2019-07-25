/** 
 * Method that will set specified field, replacing old value, if any. Note that this is identical to  {@link #replace(String,JsonNode)}, except for return value.
 * @param value to set field to; if null, will be convertedto a  {@link NullNode} first  (to remove field entry, call{@link #remove} instead)
 * @return This node after adding/replacing property value (to allow chaining)
 */
public ObjectNode set(String fieldName,JsonNode value){
  if (value == null) {
    value=nullNode();
  }
  _children.put(fieldName,value);
  return this;
}
