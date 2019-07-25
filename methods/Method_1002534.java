public BatchUpdateRequestBuilder<K,V> inputs(Map<K,V> entities){
  addKeys(entities.keySet());
  for (  Map.Entry<K,V> entry : entities.entrySet()) {
    K key=entry.getKey();
    V value=entry.getValue();
    _updateInputMap.put(key,value);
  }
  return this;
}
