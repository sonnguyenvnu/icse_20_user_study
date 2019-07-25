public static <A>Effect1<A> lazy(final F<A,Unit> f){
  return f::f;
}
