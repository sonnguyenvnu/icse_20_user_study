private CompletableFuture<Void> _XXXXX_(final LogMetadata logMetadata,final String readLockPath){
  final CompletableFuture<Void> promise=new CompletableFuture<Void>();
  promise.whenComplete((value,cause) -> {
    if (cause instanceof CancellationException) {
      FutureUtils.completeExceptionally(promise,new LockCancelledException(readLockPath,"Could not ensure read lock path",cause));
    }
  }
);
  Optional<String> parentPathShouldNotCreate=Optional.of(logMetadata.getLogRootPath());
  Utils.zkAsyncCreateFullPathOptimisticRecursive(zooKeeperClient,readLockPath,parentPathShouldNotCreate,new byte[0],zooKeeperClient.getDefaultACL(),CreateMode.PERSISTENT,new org.apache.zookeeper.AsyncCallback.StringCallback(){
    @Override public void processResult(    final int rc,    final String path,    Object ctx,    String name){
      if (KeeperException.Code.NONODE.intValue() == rc) {
        FutureUtils.completeExceptionally(promise,new LogNotFoundException(String.format("Log %s does not exist or has been deleted",logMetadata.getFullyQualifiedName())));
      }
 else       if (KeeperException.Code.OK.intValue() == rc) {
        FutureUtils.complete(promise,null);
        LOG.trace("Created path {}.",path);
      }
 else       if (KeeperException.Code.NODEEXISTS.intValue() == rc) {
        FutureUtils.complete(promise,null);
        LOG.trace("Path {} is already existed.",path);
      }
 else       if (DistributedLogConstants.ZK_CONNECTION_EXCEPTION_RESULT_CODE == rc) {
        FutureUtils.completeExceptionally(promise,new ZooKeeperClient.ZooKeeperConnectionException(path));
      }
 else       if (DistributedLogConstants.DL_INTERRUPTED_EXCEPTION_RESULT_CODE == rc) {
        FutureUtils.completeExceptionally(promise,new DLInterruptedException(path));
      }
 else {
        FutureUtils.completeExceptionally(promise,KeeperException.create(KeeperException.Code.get(rc)));
      }
    }
  }
,null);
  return promise;
}