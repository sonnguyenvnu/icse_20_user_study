@Override public void handle(Context context) throws Exception {
  HystrixCommandMetricsBroadcaster commandMetricsBroadcasterbroadcaster=context.get(HystrixCommandMetricsBroadcaster.class);
  HystrixCommandMetricsJsonMapper commandMetricsMapper=context.get(HystrixCommandMetricsJsonMapper.class);
  HystrixThreadPoolMetricsBroadcaster threadPoolMetricsBroadcaster=context.get(HystrixThreadPoolMetricsBroadcaster.class);
  HystrixThreadPoolMetricsJsonMapper threadPoolMetricsMapper=context.get(HystrixThreadPoolMetricsJsonMapper.class);
  HystrixCollapserMetricsBroadcaster collapserMetricsBroadcaster=context.get(HystrixCollapserMetricsBroadcaster.class);
  HystrixCollapserMetricsJsonMapper collapserMetricsMapper=context.get(HystrixCollapserMetricsJsonMapper.class);
  Publisher<String> metricsStream=merge(fanOut(commandMetricsBroadcasterbroadcaster).map(commandMetricsMapper),fanOut(threadPoolMetricsBroadcaster).filter(hasExecutedCommandsOnThread()).map(threadPoolMetricsMapper),fanOut(collapserMetricsBroadcaster).map(collapserMetricsMapper));
  context.render(serverSentEvents(metricsStream,spec -> spec.data(spec.getItem())));
}
