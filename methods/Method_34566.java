protected Monitor<?> getRollingCountForEvent(String name,final HystrixMetrics metrics,final HystrixRollingNumberEvent event){
  return new GaugeMetric(MonitorConfig.builder(name).withTag(DataSourceLevel.DEBUG).withTag(getServoTypeTag()).withTag(getServoInstanceTag()).build()){
    @Override public Number getValue(){
      return metrics.getRollingCount(event);
    }
  }
;
}
