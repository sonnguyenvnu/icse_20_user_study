public static <V>Iterator<V> singleton(V val){
  return new Iterator<V>(){
    @Override public boolean hasNext(){
      return !consumed;
    }
    @Override public V next(){
      if (!consumed) {
        consumed=true;
        return val;
      }
 else {
        throw new NoSuchElementException();
      }
    }
  }
;
}
