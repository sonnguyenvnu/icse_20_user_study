/** 
 * {@inheritDoc}
 */
@Override default <Z>Fn6<Z,A,B,C,D,E,F> widen(){
  return fn6(constantly(this));
}
