/** 
 * {@inheritDoc}
 */
@Override public <C>Tagged<S,C> pure(C c){
  return new Tagged<>(c);
}
