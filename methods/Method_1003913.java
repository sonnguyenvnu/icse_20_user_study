@Override public synchronized boolean unregister(Counter counter){
  String key=counter.getName();
  return counters.remove(key) != null;
}
