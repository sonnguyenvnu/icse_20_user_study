/** 
 * {@inheritDoc}
 */
@Override default <Z>Fn7<Z,A,B,C,D,E,F,G> widen(){
  return fn7(constantly(this));
}
