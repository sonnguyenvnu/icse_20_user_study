@Override public IMap<K,V> forked(){
  return new Maps.VirtualMap<>(this);
}
