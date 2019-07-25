public static <S,T,A,B>Fn2<Fn1<? super A,? extends B>,S,T> over(Optic<? super Fn1<?,?>,? super Identity<?>,S,T,A,B> optic){
  return Over.<S,T,A,B>over().apply(optic);
}
