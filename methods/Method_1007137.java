public State<S,A> withs(F<S,S> f){
  return suspended(s -> runF.f(f.f(s)));
}
