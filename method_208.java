CompletableFuture<DLSN> _XXXXX_(){
  final CompletableFuture<DLSN> result=new CompletableFuture<DLSN>();
  try {
    logger.debug("Reading last commit position from path {}",zkPath);
    zooKeeperClient.get().getData(zkPath,false,new AsyncCallback.DataCallback(){
      @Override public void processResult(      int rc,      String path,      Object ctx,      byte[] data,      Stat stat){
        logger.debug("Read last commit position from path {}: rc = {}",zkPath,rc);
        if (KeeperException.Code.NONODE.intValue() == rc) {
          result.complete(DLSN.NonInclusiveLowerBound);
        }
 else         if (KeeperException.Code.OK.intValue() != rc) {
          result.completeExceptionally(KeeperException.create(KeeperException.Code.get(rc),path));
        }
 else {
          try {
            DLSN dlsn=DLSN.deserialize(new String(data,Charsets.UTF_8));
            result.complete(dlsn);
          }
 catch (          Exception t) {
            logger.warn("Invalid last commit position found from path {}",zkPath,t);
            result.complete(DLSN.NonInclusiveLowerBound);
          }
        }
      }
    }
,null);
  }
 catch (  ZooKeeperClient.ZooKeeperConnectionException zkce) {
    result.completeExceptionally(zkce);
  }
catch (  InterruptedException ie) {
    Thread.currentThread().interrupt();
    result.completeExceptionally(new DLInterruptedException("getLastCommitPosition was interrupted",ie));
  }
  return result;
}