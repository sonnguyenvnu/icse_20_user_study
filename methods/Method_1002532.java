public BatchPartialUpdateEntityRequestBuilder<K,V> inputs(Map<K,PatchRequest<V>> patches){
  addKeys(patches.keySet());
  for (  Map.Entry<K,PatchRequest<V>> entry : patches.entrySet()) {
    K key=entry.getKey();
    PatchRequest<V> value=entry.getValue();
    _partialUpdateInputMap.put(key,value);
  }
  return this;
}
