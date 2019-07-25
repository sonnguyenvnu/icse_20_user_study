/** 
 * Specialize this choice's projection to a  {@link Tuple8}.
 * @return a {@link Tuple8}
 */
@Override public Tuple8<Maybe<A>,Maybe<B>,Maybe<C>,Maybe<D>,Maybe<E>,Maybe<F>,Maybe<G>,Maybe<H>> project(){
  return into8(HList::tuple,CoProduct8.super.project());
}
