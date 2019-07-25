/** 
 * Specialize this choice's projection to a  {@link Tuple2}.
 * @return a {@link Tuple2}
 */
@Override public Tuple2<Maybe<A>,Maybe<B>> project(){
  return into(HList::tuple,CoProduct2.super.project());
}
