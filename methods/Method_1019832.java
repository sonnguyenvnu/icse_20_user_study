@Override public SamplingStatus sample(SofaTracerSpan sofaTracerSpan){
  SamplingStatus samplingStatus=new SamplingStatus();
  Map<String,Object> tags=new HashMap<String,Object>();
  tags.put(SofaTracerConstant.SAMPLER_TYPE_TAG_KEY,TYPE);
  tags.put(SofaTracerConstant.SAMPLER_PARAM_TAG_KEY,configuration.getPercentage());
  tags=Collections.unmodifiableMap(tags);
  samplingStatus.setTags(tags);
  if (this.configuration.getPercentage() == 0) {
    samplingStatus.setSampled(false);
    return samplingStatus;
  }
 else   if (this.configuration.getPercentage() == 100) {
    samplingStatus.setSampled(true);
    return samplingStatus;
  }
  boolean result=this.sampleDecisions.get((int)(this.counter.getAndIncrement() % 100));
  samplingStatus.setSampled(result);
  return samplingStatus;
}
