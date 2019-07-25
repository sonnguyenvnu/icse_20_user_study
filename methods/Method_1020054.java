public static <A>A endo(Fn1<A,A> f,Fn1<A,A> g,A a){
  return endo(f,g).apply(a);
}
