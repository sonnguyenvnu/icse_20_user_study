public static <S,T,A,B>Fn2<B,S,T> set(Optic<? super Fn1<?,?>,? super Identity<?>,S,T,A,B> optic){
  return Set.<S,T,A,B>set().apply(optic);
}
