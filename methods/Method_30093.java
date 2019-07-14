public static <I extends Iterator<T>,T>int findIndexRemaining(I iterator,TriPredicate<T,Integer,I> predicate){
  int index=0;
  while (iterator.hasNext()) {
    T t=iterator.next();
    if (predicate.test(t,index,iterator)) {
      return index;
    }
    ++index;
  }
  return -1;
}
