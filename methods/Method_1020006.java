@Override public BiPredicate<A,A> apply(Fn1<? super A,? extends B> compareFn){
  return Fn3.super.apply(compareFn)::apply;
}
