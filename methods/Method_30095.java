public static <T,U,J extends Collection<? super U>>J mapRemaining(Iterator<T> iterator,BiFunction<T,Integer,U> function,J collector){
  int index=0;
  while (iterator.hasNext()) {
    T t=iterator.next();
    collector.add(function.apply(t,index));
    ++index;
  }
  return collector;
}
