/** 
 * {@inheritDoc}
 */
@Override public Counter createCounter(){
  SampledRateCounterImpl sampledRateCounter=new SampledRateCounterImpl(this);
  sampledRateCounter.setValue(initialNumeratorValue,initialDenominatorValue);
  return sampledRateCounter;
}
