public static <A>Maybe<A> head(Iterable<A> as){
  return Head.<A>head().apply(as);
}
