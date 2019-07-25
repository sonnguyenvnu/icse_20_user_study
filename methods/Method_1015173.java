@Override public IList<V> forked(){
  return new Lists.VirtualList<>(this);
}
