/** 
 * {@inheritDoc}
 */
@Override public <C>Market<A,B,Choice2<C,S>,Choice2<C,T>> cocartesian(){
  return new Market<>(bt.fmap(Choice2::b),cs -> cs.fmap(sta).match(c -> left(a(c)),tOrA -> tOrA.match(t -> left(b(t)),Either::right)));
}
