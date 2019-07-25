/** 
 * {@inheritDoc}
 */
@Override default <B>Monad<B,M> fmap(Fn1<? super A,? extends B> fn){
  return flatMap(fn.fmap(this::pure));
}
