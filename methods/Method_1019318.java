/** 
 * {@inheritDoc}
 */
@Override public int size(){
  return assigned + (hasEmptyKey ? 1 : 0);
}
