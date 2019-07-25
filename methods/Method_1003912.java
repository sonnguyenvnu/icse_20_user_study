@Override public synchronized boolean unregister(Gauge<?> gauge){
  String key=gauge.getName();
  return gauges.remove(key) != null;
}
