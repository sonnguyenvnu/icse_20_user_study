@Override public V merge(K key,V newValue,BiFunction<? super V,? super V,? extends V> function){
  checkNotNull(key);
  checkNotNull(newValue);
  checkNotNull(function);
  return compute(key,(k,oldValue) -> (oldValue == null) ? newValue : function.apply(oldValue,newValue));
}
