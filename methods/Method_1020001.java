public static <A,B>Fn1<B,Tuple2<A,B>> tupler(A a){
  return Tupler2.<A,B>tupler().apply(a);
}
