public Seq<A> filter(F<A,Boolean> f){
  return foldLeft((acc,a) -> f.f(a) ? acc.snoc(a) : acc,empty());
}
