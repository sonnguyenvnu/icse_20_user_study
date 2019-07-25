/** 
 * {@inheritDoc}
 */
@Override default BiPredicate<B,A> flip(){
  return Fn2.super.flip()::apply;
}
