/** 
 * Method for replacing value of specific property with passed value, and returning value (or null if none).
 * @param fieldName Property of which value to replace
 * @param value Value to set property to, replacing old value if any
 * @return Old value of the property; null if there was no such propertywith value
 */
public JsonNode replace(String fieldName,JsonNode value){
  if (value == null) {
    value=nullNode();
  }
  return _children.put(fieldName,value);
}
