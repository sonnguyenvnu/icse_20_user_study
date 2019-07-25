@Override public void ping(KeeperInstanceMeta keeperInstanceMeta){
  lastHeartBeatTime=System.currentTimeMillis();
  if (isAlive.compareAndSet(false,true)) {
    keeperAlive();
  }
  cancelFuture();
  scheduleTimeout();
}
