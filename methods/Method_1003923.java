@Override public synchronized void released(K key){
  Preconditions.checkNotNull(key);
  TrafficInfo info=trafficInfos.getUnchecked(key);
  Preconditions.checkState(info.getConnectionCount() > 0,"Double release detected!");
  info.decConnections();
}
