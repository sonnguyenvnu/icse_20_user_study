/** 
 * Specialize this choice's projection to a  {@link Tuple7}.
 * @return a {@link Tuple7}
 */
@Override public Tuple7<Maybe<A>,Maybe<B>,Maybe<C>,Maybe<D>,Maybe<E>,Maybe<F>,Maybe<G>> project(){
  return into7(HList::tuple,CoProduct7.super.project());
}
