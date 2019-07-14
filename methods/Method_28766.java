@Override public List<String> clusterSlaves(final String nodeId){
  checkIsInMultiOrPipeline();
  client.clusterSlaves(nodeId);
  return client.getMultiBulkReply();
}
