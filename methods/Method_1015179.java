@Override public IEntry<K,V> nth(long index){
  int idx=((int)index) << 1;
  return new Maps.Entry<>((K)entries[idx],(V)entries[idx + 1]);
}
