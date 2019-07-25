/** 
 * {@inheritDoc}
 */
@Override default Predicate<? super Product2<? extends A,? extends B>> uncurry(){
  return Fn2.super.uncurry()::apply;
}
