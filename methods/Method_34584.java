protected void createCurrentValueGauge(final String name,final Func0<Integer> metricToEvaluate){
  metricsRegistry.newGauge(createMetricName(name),new Gauge<Integer>(){
    @Override public Integer value(){
      return metricToEvaluate.call();
    }
  }
);
}
