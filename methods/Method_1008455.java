public PublishClusterStateStats stats(){
  return new PublishClusterStateStats(fullClusterStateReceivedCount.get(),incompatibleClusterStateDiffReceivedCount.get(),compatibleClusterStateDiffReceivedCount.get());
}
