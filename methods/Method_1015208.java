@Override public Map<K,V> linear(){
  if (isLinear()) {
    return this;
  }
 else {
    return new Map<>(root,hashFn,equalsFn,true);
  }
}
