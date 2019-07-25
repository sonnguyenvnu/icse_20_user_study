@Override public void publish(ClusterChangedEvent clusterChangedEvent,AckListener ackListener){
  ClusterState newState=clusterChangedEvent.state();
  assert newState.getNodes().isLocalNodeElectedMaster() : "Shouldn't publish state when not master " + clusterChangedEvent.source();
  if (clusterChangedEvent.previousState() != this.committedState.get()) {
    throw new FailedToCommitClusterStateException("state was mutated while calculating new CS update");
  }
  pendingStatesQueue.addPending(newState);
  try {
    publishClusterState.publish(clusterChangedEvent,electMaster.minimumMasterNodes(),ackListener);
  }
 catch (  FailedToCommitClusterStateException t) {
    logger.debug("failed to publish cluster state version [{}] (not enough nodes acknowledged, min master nodes [{}])",newState.version(),electMaster.minimumMasterNodes());
synchronized (stateMutex) {
      pendingStatesQueue.failAllStatesAndClear(new ElasticsearchException("failed to publish cluster state"));
      rejoin("zen-disco-failed-to-publish");
    }
    throw t;
  }
  final DiscoveryNode localNode=newState.getNodes().getLocalNode();
  final CountDownLatch latch=new CountDownLatch(1);
  final AtomicBoolean processedOrFailed=new AtomicBoolean();
  pendingStatesQueue.markAsCommitted(newState.stateUUID(),new PendingClusterStatesQueue.StateProcessedListener(){
    @Override public void onNewClusterStateProcessed(){
      processedOrFailed.set(true);
      latch.countDown();
      ackListener.onNodeAck(localNode,null);
    }
    @Override public void onNewClusterStateFailed(    Exception e){
      processedOrFailed.set(true);
      latch.countDown();
      ackListener.onNodeAck(localNode,e);
      logger.warn((org.apache.logging.log4j.util.Supplier<?>)() -> new ParameterizedMessage("failed while applying cluster state locally [{}]",clusterChangedEvent.source()),e);
    }
  }
);
synchronized (stateMutex) {
    if (clusterChangedEvent.previousState() != this.committedState.get()) {
      throw new FailedToCommitClusterStateException("local state was mutated while CS update was published to other nodes");
    }
    boolean sentToApplier=processNextCommittedClusterState("master " + newState.nodes().getMasterNode() + " committed version [" + newState.version() + "] source [" + clusterChangedEvent.source() + "]");
    if (sentToApplier == false && processedOrFailed.get() == false) {
      assert false : "cluster state published locally neither processed nor failed: " + newState;
      logger.warn("cluster state with version [{}] that is published locally has neither been processed nor failed",newState.version());
      return;
    }
  }
  try {
    latch.await();
  }
 catch (  InterruptedException e) {
    logger.debug((org.apache.logging.log4j.util.Supplier<?>)() -> new ParameterizedMessage("interrupted while applying cluster state locally [{}]",clusterChangedEvent.source()),e);
    Thread.currentThread().interrupt();
  }
}
