private static void writeCollapserConfigJson(JsonGenerator json,HystrixCollapserKey collapserKey,HystrixCollapserConfiguration collapserConfig) throws IOException {
  json.writeObjectFieldStart(collapserKey.name());
  json.writeNumberField("maxRequestsInBatch",collapserConfig.getMaxRequestsInBatch());
  json.writeNumberField("timerDelayInMilliseconds",collapserConfig.getTimerDelayInMilliseconds());
  json.writeBooleanField("requestCacheEnabled",collapserConfig.isRequestCacheEnabled());
  json.writeObjectFieldStart("metrics");
  HystrixCollapserConfiguration.CollapserMetricsConfig metricsConfig=collapserConfig.getCollapserMetricsConfig();
  json.writeNumberField("percentileBucketSizeInMilliseconds",metricsConfig.getRollingPercentileBucketSizeInMilliseconds());
  json.writeNumberField("percentileBucketCount",metricsConfig.getRollingCounterNumberOfBuckets());
  json.writeBooleanField("percentileEnabled",metricsConfig.isRollingPercentileEnabled());
  json.writeNumberField("counterBucketSizeInMilliseconds",metricsConfig.getRollingCounterBucketSizeInMilliseconds());
  json.writeNumberField("counterBucketCount",metricsConfig.getRollingCounterNumberOfBuckets());
  json.writeEndObject();
  json.writeEndObject();
}
