/** 
 * {@inheritDoc}
 */
public TimeStampedCounterValue getMostRecentSample(){
  return this.history.peek();
}
