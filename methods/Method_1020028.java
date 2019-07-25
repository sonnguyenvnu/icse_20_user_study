@Override public <C,App extends Applicative<?,App>,TravB extends Traversable<C,RecursiveResult<A,?>>,AppTrav extends Applicative<TravB,App>>AppTrav traverse(Fn1<? super B,? extends Applicative<C,App>> fn,Fn1<? super TravB,? extends AppTrav> pure){
  return match(__ -> pure.apply(coerce()),b -> fn.apply(b).fmap(this::pure).<TravB>fmap(RecursiveResult::coerce).coerce());
}
