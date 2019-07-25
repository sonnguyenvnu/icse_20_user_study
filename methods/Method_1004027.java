@Override public SampleListener distribution(String id,String... tagNameValuePairs){
  DistributionSummary summary=registry.distributionSummary(suffixBaseId(id).withTags(tagNameValuePairs));
  return value -> summary.record(value.longValue());
}
