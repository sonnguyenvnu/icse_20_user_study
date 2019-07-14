protected Monitor<Number> getRollingMonitor(final String name,final HystrixEventType event){
  return new GaugeMetric(MonitorConfig.builder(name).withTag(DataSourceLevel.DEBUG).withTag(getServoTypeTag()).withTag(getServoInstanceTag()).build()){
    @Override public Long getValue(){
      return metrics.getRollingCount(event);
    }
  }
;
}
