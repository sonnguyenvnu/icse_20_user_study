private static <T>Predicate<T> distinctByKey(Function<? super T,?> key){
  Set<Object> seen=ContainerUtil.newConcurrentSet();
  return t -> seen.add(key.apply(t));
}
