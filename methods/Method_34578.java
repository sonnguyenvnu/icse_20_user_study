protected void createRollingGauge(final String name,final HystrixEventType eventType){
  metricsRegistry.newGauge(createMetricName(name),new Gauge<Long>(){
    @Override public Long value(){
      return metrics.getRollingCount(HystrixRollingNumberEvent.from(eventType));
    }
  }
);
}
