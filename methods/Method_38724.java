/** 
 * Change map elements to match key and value types.
 */
protected <K,V>Map<K,V> generifyMap(final Map<Object,Object> map,final Class<K> keyType,final Class<V> valueType){
  if (keyType == String.class) {
    for (    Map.Entry<Object,Object> entry : map.entrySet()) {
      Object value=entry.getValue();
      Object newValue=convert(value,valueType);
      if (value != newValue) {
        entry.setValue(newValue);
      }
    }
    return (Map<K,V>)map;
  }
  Map<K,V> newMap=new HashMap<>(map.size());
  for (  Map.Entry<Object,Object> entry : map.entrySet()) {
    Object key=entry.getKey();
    Object newKey=convert(key,keyType);
    Object value=entry.getValue();
    Object newValue=convert(value,valueType);
    newMap.put((K)newKey,(V)newValue);
  }
  return newMap;
}
