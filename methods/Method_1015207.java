@Override public Map<K,V> forked(){
  if (isLinear()) {
    return new Map<>(root,hashFn,equalsFn,false);
  }
 else {
    return this;
  }
}
