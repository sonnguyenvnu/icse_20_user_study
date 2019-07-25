public <C>POptional<P2<S,C>,P2<T,C>,P2<A,C>,P2<B,C>> first(){
  return pOptional(sc -> getOrModify(sc._1()).bimap(t -> P.p(t,sc._2()),a -> P.p(a,sc._2())),bc -> s_ -> P.p(set(bc._1()).f(s_._1()),bc._2()));
}
