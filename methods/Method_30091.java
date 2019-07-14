public static <T>int findIndexRemaining(Iterator<T> iterator,Predicate<T> predicate){
  int index=0;
  while (iterator.hasNext()) {
    T t=iterator.next();
    if (predicate.test(t)) {
      return index;
    }
    ++index;
  }
  return -1;
}
