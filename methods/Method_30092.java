public static <T>int findIndexRemaining(Iterator<T> iterator,BiPredicate<T,Integer> predicate){
  int index=0;
  while (iterator.hasNext()) {
    T t=iterator.next();
    if (predicate.test(t,index)) {
      return index;
    }
    ++index;
  }
  return -1;
}
