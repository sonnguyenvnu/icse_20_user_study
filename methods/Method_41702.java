/** 
 * {@inheritDoc}
 */
public long getAndReset(){
  return getAndSet(0L);
}
