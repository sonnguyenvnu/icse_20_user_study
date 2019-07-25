@Override public synchronized void connected(K key){
  Preconditions.checkNotNull(key);
  trafficInfos.getUnchecked(key).incConnections();
}
