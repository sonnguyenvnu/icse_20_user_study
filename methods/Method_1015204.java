@Override public Map<K,V> update(K key,UnaryOperator<V> update){
  return update(key,update,isLinear() ? editor : new Object());
}
