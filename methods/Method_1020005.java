@Override public Predicate<A> apply(Fn1<? super A,? extends B> compareFn,A y){
  return predicate(Fn3.super.apply(compareFn,y));
}
