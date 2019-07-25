@Override default IList<V> linear(){
  return new VirtualList<V>(this).linear();
}
