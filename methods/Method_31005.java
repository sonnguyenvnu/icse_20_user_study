@NonNull public Map<K,V> buildUnmodifiable(){
  Map<K,V> map=Collections.unmodifiableMap(mMap);
  mMap=null;
  return map;
}
