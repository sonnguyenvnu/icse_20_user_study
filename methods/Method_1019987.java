public static <A,B,C>Fn1<A,Tuple2<B,C>> both(Fn1<? super A,? extends B> f,Fn1<? super A,? extends C> g){
  return Both.<A,B,C>both(f).apply(g);
}
