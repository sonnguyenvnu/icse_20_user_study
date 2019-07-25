public synchronized PendingClusterStateStats stats(){
  return new PendingClusterStateStats(pendingStates.size(),0,pendingStates.size());
}
