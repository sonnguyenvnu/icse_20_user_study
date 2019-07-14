@Override public void initialize(){
  List<Monitor<?>> monitors=getServoMonitors();
  MonitorConfig commandMetricsConfig=MonitorConfig.builder("HystrixCollapser_" + key.name()).build();
  BasicCompositeMonitor commandMetricsMonitor=new BasicCompositeMonitor(commandMetricsConfig,monitors);
  DefaultMonitorRegistry.getInstance().register(commandMetricsMonitor);
  RollingCollapserBatchSizeDistributionStream.getInstance(key,properties).startCachingStreamValuesIfUnstarted();
  RollingCollapserEventCounterStream.getInstance(key,properties).startCachingStreamValuesIfUnstarted();
  CumulativeCollapserEventCounterStream.getInstance(key,properties).startCachingStreamValuesIfUnstarted();
}
