/** 
 * Returns iterator of all entries.
 */
@Override public Iterator<Map.Entry<String,V>> iterator(){
  final MapEntry[] e={head.after};
  return new Iterator<Map.Entry<String,V>>(){
    @Override public boolean hasNext(){
      return e[0] != head;
    }
    @Override @SuppressWarnings("unchecked") public Map.Entry<String,V> next(){
      if (!hasNext()) {
        throw new NoSuchElementException("No next() entry in the iteration");
      }
      MapEntry<V> next=e[0];
      e[0]=e[0].after;
      return next;
    }
    @Override public void remove(){
      throw new UnsupportedOperationException();
    }
  }
;
}
