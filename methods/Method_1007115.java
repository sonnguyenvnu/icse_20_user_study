public final <B>Getter<P2<B,S>,P2<B,A>> second(){
  return getter(p -> P.p(p._1(),this.get(p._2())));
}
