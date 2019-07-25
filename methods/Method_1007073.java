public static <A,B>IO<A> left(final IO<A> io1,final IO<B> io2){
  return () -> {
    A a=io1.run();
    io2.run();
    return a;
  }
;
}
