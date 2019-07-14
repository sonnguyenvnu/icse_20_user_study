public static <T>boolean every(Iterable<T> iterable,Predicate<T> predicate){
  return FunctionalIterator.everyRemaining(iterable.iterator(),predicate);
}
