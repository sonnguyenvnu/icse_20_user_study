@Override public FloatMap<V> put(Double key,V value,BinaryOperator<V> merge){
  return put((double)key,value,merge);
}
