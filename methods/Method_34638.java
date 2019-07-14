public static HystrixCollapserConfiguration sample(HystrixCollapserKey collapserKey,HystrixCollapserProperties collapserProperties){
  CollapserMetricsConfig collapserMetricsConfig=new CollapserMetricsConfig(collapserProperties.metricsRollingPercentileWindowBuckets().get(),collapserProperties.metricsRollingPercentileWindowInMilliseconds().get(),collapserProperties.metricsRollingPercentileEnabled().get(),collapserProperties.metricsRollingStatisticalWindowBuckets().get(),collapserProperties.metricsRollingStatisticalWindowInMilliseconds().get());
  return new HystrixCollapserConfiguration(collapserKey,collapserProperties.maxRequestsInBatch().get(),collapserProperties.timerDelayInMilliseconds().get(),collapserProperties.requestCacheEnabled().get(),collapserMetricsConfig);
}
