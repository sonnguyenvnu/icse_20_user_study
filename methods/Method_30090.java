public static <T,J extends Collection<? super T>>J filterRemaining(Iterator<T> iterator,BiPredicate<T,Integer> predicate,J collector){
  int index=0;
  while (iterator.hasNext()) {
    T t=iterator.next();
    if (predicate.test(t,index)) {
      collector.add(t);
    }
    ++index;
  }
  return collector;
}
