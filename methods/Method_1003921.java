public static <K>TrafficMonitorAdapter<K> create(LoadBalancingStrategy<K> strategy,TrafficMonitor<K> monitor){
  return new TrafficMonitorAdapter<K>(strategy,monitor);
}
