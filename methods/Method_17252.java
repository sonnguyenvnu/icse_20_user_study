@Override public V merge(K key,V value,BiFunction<? super V,? super V,? extends V> remappingFunction){
  requireNonNull(remappingFunction);
  requireNonNull(value);
  return remap(key,(k,oldValue) -> (oldValue == null) ? value : statsAware(remappingFunction).apply(oldValue,value));
}
