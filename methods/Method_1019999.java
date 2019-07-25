public static <A>Iterable<A> snoc(A a,Iterable<A> as){
  return snoc(a).apply(as);
}
