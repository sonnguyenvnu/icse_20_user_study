public A apply(Fn1<A,A> f,Fn1<A,A> g,A a){
  return apply(f,g).apply(a);
}
