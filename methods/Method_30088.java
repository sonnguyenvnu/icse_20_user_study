public static <T>boolean some(Iterable<T> iterable,BiPredicate<T,Integer> predicate){
  return FunctionalIterator.someRemaining(iterable.iterator(),predicate);
}
