/** 
 * {@inheritDoc}
 */
@Override public void release(){
  assigned=0;
  hasEmptyKey=false;
  keys=null;
  values=null;
  ensureCapacity(Containers.DEFAULT_EXPECTED_ELEMENTS);
}
