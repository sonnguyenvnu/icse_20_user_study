protected Monitor<?> getCumulativeCountForEvent(String name,final HystrixMetrics metrics,final HystrixRollingNumberEvent event){
  return new CounterMetric(MonitorConfig.builder(name).withTag(getServoTypeTag()).withTag(getServoInstanceTag()).build()){
    @Override public Long getValue(){
      return metrics.getCumulativeCount(event);
    }
  }
;
}
