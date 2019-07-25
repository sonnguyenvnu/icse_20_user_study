/** 
 * Specialize this choice's projection to a  {@link Tuple5}.
 * @return a {@link Tuple5}
 */
@Override public Tuple5<Maybe<A>,Maybe<B>,Maybe<C>,Maybe<D>,Maybe<E>> project(){
  return into5(HList::tuple,CoProduct5.super.project());
}
