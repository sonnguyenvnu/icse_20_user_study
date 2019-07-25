/** 
 * Specialize this choice's projection to a  {@link Tuple3}.
 * @return a {@link Tuple3}
 */
@Override public Tuple3<Maybe<A>,Maybe<B>,Maybe<C>> project(){
  return into3(HList::tuple,CoProduct3.super.project());
}
