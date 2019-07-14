public static <T,J extends Collection<? super T>>J filterRemaining(Iterator<T> iterator,Predicate<T> predicate,J collector){
  while (iterator.hasNext()) {
    T t=iterator.next();
    if (predicate.test(t)) {
      collector.add(t);
    }
  }
  return collector;
}
