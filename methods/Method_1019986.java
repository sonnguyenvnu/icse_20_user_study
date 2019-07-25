public static <A,B,C>Fn1<Fn1<? super A,? extends C>,Fn1<A,Tuple2<B,C>>> both(Fn1<? super A,? extends B> f){
  return Both.<A,B,C>both().apply(f);
}
