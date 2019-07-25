public static <A>Iterable<Iterable<A>> slide(Integer k,Iterable<A> as){
  return Slide.<A>slide(k).apply(as);
}
