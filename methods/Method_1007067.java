@Override public P2<Integer,A> lookup(F<V,Integer> o,int i){
  final F<A,V> m=measured().measure();
  final int s1=o.f(m.f(as._1()));
  if (i < s1) {
    return P.p(i,as._1());
  }
 else {
    return P.p(i - s1,as._2());
  }
}
