/** 
 * Returns the value with the specified key, as an object.
 */
@SuppressWarnings("unchecked") public <T>T getValue(final String key){
  T val=(T)map.get(key);
  if (val instanceof Map) {
    return (T)new JsonObject((Map)val);
  }
  if (val instanceof List) {
    return (T)new JsonArray((List)val);
  }
  return val;
}
