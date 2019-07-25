public static <A>Iterable<A> intersperse(A a,Iterable<A> as){
  return intersperse(a).apply(as);
}
