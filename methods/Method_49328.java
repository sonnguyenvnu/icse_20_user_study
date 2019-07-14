public static <O>Iterable<O> limitedIterable(final Iterable<O> iterable,final int limit){
  return StreamSupport.stream(iterable.spliterator(),false).limit(limit).collect(Collectors.toList());
}
