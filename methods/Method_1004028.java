@Override public void gauge(String id,Supplier<Number> supplier,String... tagNameValuePairs){
  Id metricId=suffixBaseId(id).withTags(tagNameValuePairs);
  PolledMeter.remove(registry,metricId);
  PolledMeter.using(registry).withId(metricId).monitorValue(supplier,ignore -> supplier.get().doubleValue());
}
