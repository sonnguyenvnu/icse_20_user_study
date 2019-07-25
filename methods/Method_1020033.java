/** 
 * {@inheritDoc}
 */
@Override default <Z>BiPredicate<Z,A> widen(){
  return Fn1.super.widen()::checkedApply;
}
