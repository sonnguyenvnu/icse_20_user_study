protected Monitor<Number> getShardSizeMeanMonitor(final String name){
  return new GaugeMetric(MonitorConfig.builder(name).build()){
    @Override public Number getValue(){
      return metrics.getShardSizeMean();
    }
  }
;
}
