@Override public LinearMap<K,V> remove(K key){
  int idx=tableIndex(keyHash(key),key);
  if (idx >= 0) {
    long row=table[idx];
    size--;
    int keyIndex=Row.keyIndex(row);
    int lastKeyIndex=size << 1;
    if (keyIndex != lastKeyIndex) {
      K lastKey=(K)entries[lastKeyIndex];
      V lastValue=(V)entries[lastKeyIndex + 1];
      int lastIdx=tableIndex(keyHash(lastKey),lastKey);
      table[lastIdx]=Row.construct(Row.hash(table[lastIdx]),keyIndex);
      putEntry(keyIndex,lastKey,lastValue);
    }
    table[idx]=Row.addTombstone(row);
    putEntry(lastKeyIndex,null,null);
    hash=-1;
  }
  return this;
}
