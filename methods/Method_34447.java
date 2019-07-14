protected void createRollingCountForEvent(final String name,final HystrixRollingNumberEvent event){
  metricRegistry.register(createMetricName(name),new Gauge<Long>(){
    @Override public Long getValue(){
      return metrics.getRollingCount(event);
    }
  }
);
}
