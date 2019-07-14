protected void createTotalLatencyPercentileGauge(final String name,final double percentile){
  metricsRegistry.newGauge(createMetricName(name),new Gauge<Integer>(){
    @Override public Integer value(){
      return metrics.getTotalTimePercentile(percentile);
    }
  }
);
}
