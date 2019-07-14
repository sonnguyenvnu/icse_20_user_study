protected void createCumulativeGauge(final String name,final HystrixEventType eventType){
  metricsRegistry.newGauge(createMetricName(name),new Gauge<Long>(){
    @Override public Long value(){
      return metrics.getCumulativeCount(HystrixRollingNumberEvent.from(eventType));
    }
  }
);
}
