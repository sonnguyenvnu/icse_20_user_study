/** 
 * Specialize this choice's projection to a  {@link Tuple4}.
 * @return a {@link Tuple4}
 */
@Override public Tuple4<Maybe<A>,Maybe<B>,Maybe<C>,Maybe<D>> project(){
  return into4(HList::tuple,CoProduct4.super.project());
}
