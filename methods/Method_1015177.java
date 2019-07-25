@Override public LinearMap<K,V> update(K key,UnaryOperator<V> update){
  int idx=tableIndex(keyHash(key),key);
  if (idx >= 0) {
    long row=table[idx];
    int valIdx=Row.keyIndex(row) + 1;
    entries[valIdx]=update.apply((V)entries[valIdx]);
    hash=-1;
  }
 else {
    put(key,update.apply(null));
  }
  return this;
}
