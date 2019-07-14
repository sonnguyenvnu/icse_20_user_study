/** 
 * {@inheritDoc}
 */
public TimeStampedCounterValue[] getAllSampleValues(){
  return this.history.toArray(new TimeStampedCounterValue[this.history.depth()]);
}
