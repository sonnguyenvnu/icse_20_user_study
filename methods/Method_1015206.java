@Override public IEntry<K,V> nth(long index){
  if (index < 0 || index >= size()) {
    throw new IndexOutOfBoundsException();
  }
  return root.nth(index);
}
