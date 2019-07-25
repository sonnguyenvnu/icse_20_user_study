/** 
 * {@inheritDoc}
 */
@Override default <Z>Fn3<Z,A,B,C> widen(){
  return fn3(constantly(this));
}
