@Override public Iterator<Entry> reuseIterator(){
  return new SwappingEntry();
}
