public final <B>Getter<P2<S,B>,P2<A,B>> first(){
  return getter(p -> P.p(this.get(p._1()),p._2()));
}
