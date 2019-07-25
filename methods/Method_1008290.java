public static <T>Iterator<T> concat(Iterator<? extends T>... iterators){
  if (iterators == null) {
    throw new NullPointerException("iterators");
  }
  return new ConcatenatedIterator<T>(iterators);
}
