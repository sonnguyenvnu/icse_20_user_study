/** 
 * Store the shorter of the incoming value or the existing value in the map at the key specified.
 * @param map
 * @param key
 * @param value
 */
private void storeShortest(Map<Class<?>,String> map,Class<?> key,String value){
  String existingValue=map.get(key);
  if (existingValue == null) {
    map.put(key,value);
    return;
  }
  if (existingValue.length() < value.length()) {
    return;
  }
  map.put(key,value);
}
