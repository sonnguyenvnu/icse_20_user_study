/** 
 * {@inheritDoc}
 */
@Override default <Z>Fn4<Z,A,B,C,D> widen(){
  return fn4(constantly(this));
}
