/** 
 * {@inheritDoc}
 */
@Override public Counter createCounter(){
  return new SampledCounterImpl(this);
}
