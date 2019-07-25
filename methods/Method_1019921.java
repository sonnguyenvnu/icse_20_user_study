/** 
 * Specialize this choice's projection to a  {@link Tuple6}.
 * @return a {@link Tuple6}
 */
@Override public Tuple6<Maybe<A>,Maybe<B>,Maybe<C>,Maybe<D>,Maybe<E>,Maybe<F>> project(){
  return into6(HList::tuple,CoProduct6.super.project());
}
