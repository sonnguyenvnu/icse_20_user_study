public V interned(K key){
  V existingKey=storage.get(key);
  V newKey=null;
  if (existingKey == null) {
    newKey=valueConstructor.create(key);
    existingKey=storage.putIfAbsent(key,newKey);
  }
  return existingKey != null ? existingKey : newKey;
}
