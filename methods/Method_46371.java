private boolean parseSampled(Map<String,String> contextMap,SofaTracerSpanContext spanContext){
  String oldSampledMark=spanContext.getSysBaggage().get(TracerCompatibleConstants.SAMPLING_MARK);
  if (StringUtils.isBlank(oldSampledMark) || "true".equals(oldSampledMark)) {
    return true;
  }
  String sampledStr=this.getEmptyStringIfNull(contextMap,TracerCompatibleConstants.SAMPLING_MARK);
  return StringUtils.isNotBlank(sampledStr) ? Boolean.valueOf(sampledStr) : true;
}
