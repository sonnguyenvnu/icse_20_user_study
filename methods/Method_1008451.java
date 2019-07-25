@Override public synchronized void publish(final ClusterChangedEvent event,final AckListener ackListener){
  clusterState=event.state();
  CountDownLatch latch=new CountDownLatch(1);
  ClusterStateTaskListener listener=new ClusterStateTaskListener(){
    @Override public void clusterStateProcessed(    String source,    ClusterState oldState,    ClusterState newState){
      latch.countDown();
      ackListener.onNodeAck(transportService.getLocalNode(),null);
    }
    @Override public void onFailure(    String source,    Exception e){
      latch.countDown();
      ackListener.onNodeAck(transportService.getLocalNode(),e);
      logger.warn((org.apache.logging.log4j.util.Supplier<?>)() -> new ParameterizedMessage("failed while applying cluster state locally [{}]",event.source()),e);
    }
  }
;
  clusterApplier.onNewClusterState("apply-locally-on-node[" + event.source() + "]",() -> clusterState,listener);
  try {
    latch.await();
  }
 catch (  InterruptedException e) {
    Thread.currentThread().interrupt();
  }
}
