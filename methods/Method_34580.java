protected void createExecutionLatencyMeanGauge(final String name){
  metricsRegistry.newGauge(createMetricName(name),new Gauge<Integer>(){
    @Override public Integer value(){
      return metrics.getExecutionTimeMean();
    }
  }
);
}
