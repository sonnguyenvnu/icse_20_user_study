public static <T>boolean some(Iterable<T> iterable,Predicate<T> predicate){
  return FunctionalIterator.someRemaining(iterable.iterator(),predicate);
}
