@Override public Iterator<Value> iterator(){
  return values != null ? values.iterator() : Collections.<Value>emptyIterator();
}
