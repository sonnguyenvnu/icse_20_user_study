@Override public IEntry<Long,V> nth(long index){
  return index < neg.size() ? neg.nth(index) : pos.nth(index - neg.size());
}
