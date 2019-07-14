public static <T,U,J extends Collection<? super U>>J mapRemaining(Iterator<T> iterator,Function<T,U> function,J collector){
  while (iterator.hasNext()) {
    T t=iterator.next();
    collector.add(function.apply(t));
  }
  return collector;
}
