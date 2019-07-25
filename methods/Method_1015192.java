@Override public ISet<V> forked(){
  return new Sets.VirtualSet<>(this);
}
