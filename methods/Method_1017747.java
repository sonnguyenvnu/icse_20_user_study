<T,U extends T>T map(Function<? super L,U> ifLeft,Function<? super R,U> ifRight){
  return left.map(ifLeft).orElseGet(() -> right.map(ifRight).get());
}
