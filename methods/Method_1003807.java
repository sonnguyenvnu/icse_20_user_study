@Override public String apply(HystrixThreadPoolMetrics threadPoolMetrics) throws Exception {
  HystrixThreadPoolKey key=threadPoolMetrics.getThreadPoolKey();
  StringWriter jsonString=new StringWriter();
  JsonGenerator json=jsonFactory.createGenerator(jsonString);
  json.writeStartObject();
  json.writeStringField("type","HystrixThreadPool");
  json.writeStringField("name",key.name());
  json.writeNumberField("currentTime",System.currentTimeMillis());
  json.writeNumberField("currentActiveCount",threadPoolMetrics.getCurrentActiveCount().intValue());
  json.writeNumberField("currentCompletedTaskCount",threadPoolMetrics.getCurrentCompletedTaskCount().longValue());
  json.writeNumberField("currentCorePoolSize",threadPoolMetrics.getCurrentCorePoolSize().intValue());
  json.writeNumberField("currentLargestPoolSize",threadPoolMetrics.getCurrentLargestPoolSize().intValue());
  json.writeNumberField("currentMaximumPoolSize",threadPoolMetrics.getCurrentMaximumPoolSize().intValue());
  json.writeNumberField("currentPoolSize",threadPoolMetrics.getCurrentPoolSize().intValue());
  json.writeNumberField("currentQueueSize",threadPoolMetrics.getCurrentQueueSize().intValue());
  json.writeNumberField("currentTaskCount",threadPoolMetrics.getCurrentTaskCount().longValue());
  json.writeNumberField("rollingCountThreadsExecuted",threadPoolMetrics.getRollingCount(HystrixRollingNumberEvent.THREAD_EXECUTION));
  json.writeNumberField("rollingMaxActiveThreads",threadPoolMetrics.getRollingMaxActiveThreads());
  json.writeNumberField("rollingCountCommandRejections",threadPoolMetrics.getRollingCount(HystrixRollingNumberEvent.THREAD_POOL_REJECTED));
  json.writeNumberField("propertyValue_queueSizeRejectionThreshold",threadPoolMetrics.getProperties().queueSizeRejectionThreshold().get());
  json.writeNumberField("propertyValue_metricsRollingStatisticalWindowInMilliseconds",threadPoolMetrics.getProperties().metricsRollingStatisticalWindowInMilliseconds().get());
  json.writeNumberField("reportingHosts",1);
  json.writeEndObject();
  json.close();
  return jsonString.getBuffer().toString();
}
