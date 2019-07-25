@Override public SamplingStatus sample(SofaTracerSpan sofaTracerSpan){
  SamplingStatus samplingStatus=new SamplingStatus();
  Map<String,Object> tags=new HashMap<String,Object>();
  tags.put(SofaTracerConstant.SAMPLER_TYPE_TAG_KEY,TYPE);
  tags=Collections.unmodifiableMap(tags);
  samplingStatus.setTags(tags);
  if (sofaTracerSpan.isServer()) {
    samplingStatus.setSampled(false);
  }
 else {
    samplingStatus.setSampled(true);
  }
  return samplingStatus;
}
