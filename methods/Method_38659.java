/** 
 * Returns the object value at position  {@code pos} in the array.
 */
public Object getValue(final int pos){
  Object val=list.get(pos);
  if (val instanceof Map) {
    val=new JsonObject((Map)val);
  }
 else   if (val instanceof List) {
    val=new JsonArray((List)val);
  }
  return val;
}
