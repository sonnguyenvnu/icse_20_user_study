/** 
 * {@inheritDoc}
 */
@Override public synchronized long getAndReset(){
  long prevVal=getValue();
  setValue(0,0);
  return prevVal;
}
