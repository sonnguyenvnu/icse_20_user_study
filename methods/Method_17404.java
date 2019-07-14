@Override @SuppressWarnings("ShortCircuitBoolean") public boolean put(long e){
  int item=spread(Long.hashCode(e));
  return setAt(item,0) | setAt(item,1) | setAt(item,2) | setAt(item,3);
}
