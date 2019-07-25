LinearMap<K,V> merge(LinearMap<K,V> m,BinaryOperator<V> mergeFn){
  if (m.size > size) {
    return m.merge(this,(x,y) -> mergeFn.apply(y,x));
  }
  LinearMap<K,V> result=this.clone();
  result.resize(result.size + m.size);
  for (  long row : m.table) {
    if (Row.populated(row)) {
      int keyIndex=Row.keyIndex(row);
      result.put(Row.hash(row),(K)m.entries[keyIndex],(V)m.entries[keyIndex + 1],mergeFn);
    }
  }
  return result;
}
