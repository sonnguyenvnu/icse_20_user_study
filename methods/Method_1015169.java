@Override default ISet<V> linear(){
  return new Sets.VirtualSet<>(this).linear();
}
