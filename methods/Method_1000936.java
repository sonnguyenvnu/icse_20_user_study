/** 
 * Method to use for accessing all fields (with both names and values) of this JSON Object.
 */
@Override public Iterator<Map.Entry<String,JsonNode>> fields(){
  return _children.entrySet().iterator();
}
