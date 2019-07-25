@Override public boolean equals(IMap<K,V> m,BiPredicate<V,V> valEquals){
  if (m instanceof Map && keyHash() == m.keyHash()) {
    return root.equals(((Map<K,V>)m).root,equalsFn,valEquals);
  }
 else {
    return Maps.equals(this,m,valEquals);
  }
}
