/** 
 * Initialize zookeeper tracking for this  {@link Supplier}.  Once this call returns, this object will be tracking data in zookeeper.
 * @throws InterruptedException if the underlying zookeeper server transaction is interrupted
 * @throws KeeperException if the server signals an error
 * @throws ZooKeeperConnectionException if there was a problem connecting to the zookeepercluster
 */
@VisibleForTesting void init() throws InterruptedException, KeeperException, ZooKeeperConnectionException {
  Watcher watcher=zkClient.registerExpirationHandler(new Command(){
    @Override public void execute(){
      try {
synchronized (safeToRewatchLock) {
          if (safeToRewatch) {
            tryWatchDataNode();
          }
        }
      }
 catch (      InterruptedException e) {
        LOG.log(Level.WARNING,"Interrupted while trying to re-establish watch.",e);
        Thread.currentThread().interrupt();
      }
    }
  }
);
  try {
synchronized (safeToRewatchLock) {
      watchDataNode();
      safeToRewatch=true;
    }
  }
 catch (  InterruptedException e) {
    zkClient.unregister(watcher);
    throw e;
  }
catch (  KeeperException e) {
    zkClient.unregister(watcher);
    throw e;
  }
catch (  ZooKeeperConnectionException e) {
    zkClient.unregister(watcher);
    throw e;
  }
}
