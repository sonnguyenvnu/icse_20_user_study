@Deprecated protected void createRollingGauge(String name,final HystrixRollingNumberEvent event){
  metricsRegistry.newGauge(createMetricName(name),new Gauge<Long>(){
    @Override public Long value(){
      return metrics.getRollingCount(event);
    }
  }
);
}
