public <C>POptional<P2<C,S>,P2<C,T>,P2<C,A>,P2<C,B>> second(){
  return pOptional(cs -> getOrModify(cs._2()).bimap(t -> P.p(cs._1(),t),a -> P.p(cs._1(),a)),cb -> _s -> P.p(cb._1(),set(cb._2()).f(_s._2())));
}
