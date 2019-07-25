/** 
 * {@inheritDoc}
 */
@Override public Tuple2<Maybe<Unit>,Maybe<A>> project(){
  return CoProduct2.super.project().into(HList::tuple);
}
