@SafeVarargs @SuppressWarnings("varargs") public static <A>Iterable<A> cycle(A... as){
  return cycle(asList(as));
}
