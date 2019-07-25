/** 
 * {@inheritDoc}
 */
@Override public <C,App extends Applicative<?,App>,TravB extends Traversable<C,Const<A,?>>,AppTrav extends Applicative<TravB,App>>AppTrav traverse(Fn1<? super B,? extends Applicative<C,App>> fn,Fn1<? super TravB,? extends AppTrav> pure){
  return pure.apply(coerce());
}
