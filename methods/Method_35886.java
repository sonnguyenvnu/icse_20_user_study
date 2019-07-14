public static <T>Iterable<T> all(final Iterator<T> underlyingIterator){
  return new Iterable<T>(){
    public Iterator<T> iterator(){
      return underlyingIterator;
    }
  }
;
}
