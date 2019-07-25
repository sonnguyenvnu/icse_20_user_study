public Map<K,V> update(K key,UnaryOperator<V> update,Object editor){
  return put(key,update.apply(get(key,null)),(BinaryOperator<V>)Maps.MERGE_LAST_WRITE_WINS,editor);
}
